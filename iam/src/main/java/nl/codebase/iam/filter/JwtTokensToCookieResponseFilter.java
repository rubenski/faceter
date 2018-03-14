package nl.codebase.iam.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Removes the refresh_token and the access_token from the JSON response and returns them each as a separate http-only
 * cookie.
 *
 */
@Slf4j
public class JwtTokensToCookieResponseFilter extends OncePerRequestFilter {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Provide a response wrapper, so the original response doesn't get committed,
        // which makes it impossible to add headers

        DummyResponse wrapper = new DummyResponse(response);
        filterChain.doFilter(request, wrapper);

        AccessToken accessToken = getAccessTokenFromOriginalResponse(wrapper);

        if(accessToken.noError()) {
            response.addCookie(createCookieFromResfreshTokenInResponse(accessToken));
            response.addCookie(createCookieFromAccessTokenInResponse(accessToken));
        }

        accessToken.setAccessToken(null);
        accessToken.setRefreshToken(null);

        // Write the response bytes captured during filterChain.doFilter() back to the response output stream
        response.getOutputStream().write(mapper.writeValueAsBytes(accessToken));
    }

    /**
     * Map the raw response bytes captured in the filterChain to a AccessToken object
     *
     * @param wrapper
     * @return
     * @throws IOException
     */
    private AccessToken getAccessTokenFromOriginalResponse(DummyResponse wrapper) throws IOException {
        return mapper.readValue(wrapper.getContent(), AccessToken.class);
    }

    /**
     * Create a refresh token cookie with expiry time. The expiry time in the token is an epoch value. We must
     * convert this to seconds, because that is what the cookie expects.
     *
     * @param accessToken the acccess token
     * @return the cookie
     */
    private Cookie createCookieFromResfreshTokenInResponse(AccessToken accessToken) throws IOException {
        Cookie refreshTokenCookie = new Cookie("refresh_token", accessToken.getRefreshToken());
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(getExpirationSecondsFromToken(accessToken.getRefreshToken()));
        return refreshTokenCookie;
    }

    private Cookie createCookieFromAccessTokenInResponse(AccessToken accessToken) throws IOException {
        Cookie accessTokenCookie = new Cookie("access_token", accessToken.getAccessToken());
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setMaxAge(getExpirationSecondsFromToken(accessToken.getAccessToken()));
        return accessTokenCookie;
    }

    private int getExpirationSecondsFromToken(String accessToken) throws IOException {
        double currentEpochSeconds = Math.ceil(System.currentTimeMillis() / 1000);
        String[] refreshTokenParts = accessToken.split("\\.");
        String refreshTokenPayloadRaw = new String(Base64.getDecoder().decode(refreshTokenParts[1]));
        RefreshTokenPayload refreshTokenPayload = mapper.readValue(refreshTokenPayloadRaw, RefreshTokenPayload.class);
        int expiryEpochSeconds = refreshTokenPayload.getExpiryEpochSeconds();
        // The expiration time is in epoch seconds format, so we must subtract the current epoch seconds to get
        // the number of seconds UNTIL expiration, which is needed by the Cookie interface.
        int i = (int) (expiryEpochSeconds - currentEpochSeconds);
        log.info("Token number of seconds valid = {}", i);
        return i;
    }

    private static class DummyResponse extends HttpServletResponseWrapper {

        private List<Integer> responseBytes = new ArrayList<>();

        DummyResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {

                @Override
                public void write(int b) throws IOException {
                    responseBytes.add(b);
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener listener) {

                }
            };
        }

        String getContent() {
            byte[] bytes = new byte[responseBytes.size()];
            final int[] count = new int[]{0};
            responseBytes.forEach(b -> {
                bytes[count[0]] = (byte) ((int) b);
                count[0]++;
            });

            return new String(bytes);
        }

        @Override
        public void flushBuffer() throws IOException {
            // do nothing! This is intentional, it prevents the response form being committed too early.
            // Calling super.flushBuffer() will make response headers set after chain.foFilter() disappear.
        }
    }

    @Getter
    @Setter
    private static class AccessToken {

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("expires_in")
        private int expiresInSeconds;

        private String scope;
        private String jti;
        private String error;
        @JsonProperty("error_description")
        private String errorDescription;

        boolean noError() {
            return error == null;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    private static class RefreshTokenPayload {
        @JsonProperty("exp")
        private int expiryEpochSeconds;
    }
}
