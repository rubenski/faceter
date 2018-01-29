package nl.codebase.faceter.forms.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;

import static nl.codebase.faceter.common.FaceterConstants.CSRF_COOKIE_NAME;
import static nl.codebase.faceter.common.FaceterConstants.CSRF_HEADER_NAME;

@Slf4j
public class CsrfTokenValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(!request.getMethod().equals("GET")) {
            boolean csrfOk = false;
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                Optional<Cookie> cookieOptional = Arrays.stream(cookies).filter(c -> c.getName().equals(CSRF_COOKIE_NAME)).findFirst();
                if(cookieOptional.isPresent()) {
                    String header = request.getHeader(CSRF_HEADER_NAME);
                    if(header != null && header.equals(cookieOptional.get().getValue())) {
                        log.debug("CSRF TOKEN OK");
                        csrfOk = true;
                        filterChain.doFilter(request, response);
                    }
                }
            }

            if(!csrfOk) {
                response.sendError(403, "Forbidden");
            }
        }


    }
}