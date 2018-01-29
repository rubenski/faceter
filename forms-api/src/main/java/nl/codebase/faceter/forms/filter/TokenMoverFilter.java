package nl.codebase.faceter.forms.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Moves the JWT token from the incoming cookie to the header, so that Spring's BearerTokenExtractor will pick it up
 */
@Slf4j
public class TokenMoverFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            filterChain.doFilter(request, response);
        }
        Optional<Cookie> optionalAccessToken = Arrays.stream(cookies).filter(c -> c.getName().equals("access_token")).findFirst();
        if(optionalAccessToken.isPresent()) {
            Cookie cookie = optionalAccessToken.get();
            filterChain.doFilter(new MyRequestWrapper(request, cookie.getValue()), response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private static class MyRequestWrapper extends HttpServletRequestWrapper {


        private final String jwtToken;

        public MyRequestWrapper(HttpServletRequest request, String jwtToken) {
            super(request);
            this.jwtToken = jwtToken;
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if(name.equals("Authorization")) {
                List<String> headers = new ArrayList<>();
                headers.add("Bearer " + jwtToken);
                return Collections.enumeration(headers);
            }
            return super.getHeaders(name);
        }

        @Override
        public String getHeader(String name) {
            return super.getHeader(name);
        }
    }
}
