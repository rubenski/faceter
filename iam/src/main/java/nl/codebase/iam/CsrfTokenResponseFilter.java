package nl.codebase.iam;

import nl.codebase.faceter.common.FaceterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class CsrfTokenResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("set-cookie", String.format("%s=%s;HttpOnly; Path=/", FaceterConstants.CSRF_COOKIE_NAME, UUID.randomUUID().toString()));
        chain.doFilter(request, res);
    }

    @Override
    public void destroy() {
    }
}