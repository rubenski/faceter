package nl.codebase.faceter.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Checks if a refresh token cookie is present when the grant type is refresh_token. If not, this probably means
 * someone access a protected URL without an access token. We'd like to stop this request as early as possible, hence
 * this filter.
 */
@Slf4j
@Component
public class InvalidRequestFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        String grantType = RequestUtil.getParameter("grant_type");
        if(grantType != null && grantType.equals("refresh_token")) {
            String refreshToken = RequestUtil.getCookieValue("refresh_token");
            if(refreshToken == null) {
                throw new ZuulRuntimeException(new ZuulException("No refresh token found",
                        HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED"));
            }
        }

        return null;
    }
}
