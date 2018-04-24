package nl.codebase.faceter.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static nl.codebase.faceter.zuul.ProxyConstants.PARAM_GRANT_TYPE;
import static nl.codebase.faceter.zuul.ProxyConstants.PARAM_REFRESH_TOKEN;
import static nl.codebase.faceter.zuul.ProxyConstants.STATUS_UNAUTHORIZED;

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
        String grantType = RequestUtil.getParameter(PARAM_GRANT_TYPE);
        if(grantType != null && grantType.equals(PARAM_REFRESH_TOKEN)) {
            String refreshToken = RequestUtil.getCookieValue(PARAM_REFRESH_TOKEN);
            if(refreshToken == null) {
                throw new ZuulRuntimeException(new ZuulException("No refresh token found",
                        HttpStatus.UNAUTHORIZED.value(), STATUS_UNAUTHORIZED));
            }
        }

        return null;
    }
}
