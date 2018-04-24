package nl.codebase.faceter.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class RefreshTokenToBodyPreFilter extends ZuulFilter {

    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String GRANT_TYPE = "grant_type";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestURI = request.getRequestURI();
        return requestURI.contains("token");
    }

    private Optional<StandardMultipartHttpServletRequest> getRequest() {
        RequestContext context = RequestContext.getCurrentContext();
        InputStream stream = (InputStream) context.get("requestEntity");
        if (stream == null) {
            HttpServletRequestWrapper request = (HttpServletRequestWrapper) context.getRequest();
            HttpServletRequest request1 = request.getRequest();
            String grantType = request1.getParameter("grant_type");
            if (request.getRequest() instanceof StandardMultipartHttpServletRequest) {
                return Optional.of((StandardMultipartHttpServletRequest) request.getRequest());
            }
        }
        return Optional.empty();
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequestWrapper wrapperRequest = (HttpServletRequestWrapper) context.getRequest();
        HttpServletRequest request = wrapperRequest.getRequest();
        String grantType = request.getParameter("grant_type");

        // If this is a token refresh attempt we need to check for a cookie containing the refresh token
        if("refresh_token".equals(grantType)) {
            // get the refresh token from cookie here and set it in the body

            Cookie[] cookies = context.getRequest().getCookies();

            if(cookies == null) {
                return null;
            }

            Optional<Cookie> refreshTokenCookieOptional = Arrays.stream(cookies).filter(cookie -> "refresh_token".equals(cookie.getName())).findFirst();

            if(refreshTokenCookieOptional.isPresent()) {
                Cookie cookie = refreshTokenCookieOptional.get();
                String refreshToken = cookie.getValue();

            }

            String test = "";


            try {
                InputStream in = (InputStream) context.get("requestEntity");
                if (in == null) {
                    in = context.getRequest().getInputStream();
                }
                String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                byte[] bytes = body.getBytes("UTF-8");

                context.setRequest(new HttpServletRequestWrapper(context.getRequest()) {
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(bytes);
                    }

                    @Override
                    public int getContentLength() {
                        return bytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return bytes.length;
                    }
                });
            } catch (IOException e) {

            }

        }
        return null;
    }
}
