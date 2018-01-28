package nl.codebase.faceter.common;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Adds a CSRF token cookie header to the response
 */
public class CsrfTokenResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie c = new Cookie(FaceterConstants.CSRF_COOKIE_NAME, UUID.randomUUID().toString());
        c.setHttpOnly(false); // we need to be able to read it with the Angular client
        c.setPath("/");
        res.addCookie(c);
        chain.doFilter(request, res);
    }

    @Override
    public void destroy() {
    }
}