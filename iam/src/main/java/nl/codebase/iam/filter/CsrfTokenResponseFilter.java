package nl.codebase.iam.filter;

import nl.codebase.faceter.common.FaceterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Adds a CSRF token cookie header to the response
 */
@Component
public class CsrfTokenResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie c = new Cookie(FaceterConstants.CSRF_COOKIE_NAME, UUID.randomUUID().toString());
        c.setHttpOnly(true);
        c.setPath("/");
        res.addCookie(c);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}