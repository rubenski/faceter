package nl.codebase.iam.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
public class RubenFilter extends GenericFilterBean {

    private static final String GRANT_TYPE_PARAM = "grant_type";
    private static final String REFRESH_TOKEN_VALUE = "refresh_token";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (isTokenRefreshRequest((HttpServletRequest) request)) {
            Optional<Cookie> cookieOptional = tryGetRefreshTokenCookie((HttpServletRequest) request);
            if (cookieOptional.isPresent()) {
                String refreshToken = cookieOptional.get().getValue();
                TestRequestWrapper wrapper = new TestRequestWrapper((HttpServletRequest) request);
                chain.doFilter(wrapper, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isTokenRefreshRequest(HttpServletRequest request) {
        String grantType = request.getParameter(GRANT_TYPE_PARAM);
        return !StringUtils.isBlank(grantType) && REFRESH_TOKEN_VALUE.equals(grantType);
    }

    private Optional<Cookie> tryGetRefreshTokenCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies).filter(c -> c.getName().equals(REFRESH_TOKEN_VALUE)).findFirst();
    }

    private static class TestRequestWrapper extends HttpServletRequestWrapper {

        public TestRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return super.getReader();
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {

            log.info("test");

            new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener listener) {
                    log.info("");
                }
            };

            return super.getInputStream();
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return super.getHeaderNames();
        }

        @Override
        public ServletRequest getRequest() {
            return new MyBlaRequest(super.getRequest());
        }

        private static class MyBlaRequest implements ServletRequest {

            private ServletRequest sourceRequest;

            public MyBlaRequest(ServletRequest sourceRequest) {
                this.sourceRequest = sourceRequest;
            }

            @Override
            public Object getAttribute(String name) {
                return sourceRequest.getAttribute(name);
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return sourceRequest.getAttributeNames();
            }

            @Override
            public String getCharacterEncoding() {
                return sourceRequest.getCharacterEncoding();
            }

            @Override
            public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
                sourceRequest.setCharacterEncoding(env);
            }

            @Override
            public int getContentLength() {
                return sourceRequest.getContentLength();
            }

            @Override
            public long getContentLengthLong() {
                return sourceRequest.getContentLengthLong();
            }

            @Override
            public String getContentType() {
                return sourceRequest.getContentType();
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                return sourceRequest.getInputStream();
            }

            @Override
            public String getParameter(String name) {
                return sourceRequest.getParameter(name);
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return sourceRequest.getParameterNames();
            }

            @Override
            public String[] getParameterValues(String name) {
                return sourceRequest.getParameterValues(name);
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return sourceRequest.getParameterMap();
            }

            @Override
            public String getProtocol() {
                return sourceRequest.getProtocol();
            }

            @Override
            public String getScheme() {
                return sourceRequest.getScheme();
            }

            @Override
            public String getServerName() {
                return sourceRequest.getServerName();
            }

            @Override
            public int getServerPort() {
                return sourceRequest.getServerPort();
            }

            @Override
            public BufferedReader getReader() throws IOException {
                return sourceRequest.getReader();
            }

            @Override
            public String getRemoteAddr() {
                return sourceRequest.getRemoteAddr();
            }

            @Override
            public String getRemoteHost() {
                return sourceRequest.getRemoteHost();
            }

            @Override
            public void setAttribute(String name, Object o) {
                sourceRequest.setAttribute(name, o);
            }

            @Override
            public void removeAttribute(String name) {
                sourceRequest.removeAttribute(name);
            }

            @Override
            public Locale getLocale() {
                return sourceRequest.getLocale();
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return sourceRequest.getLocales();
            }

            @Override
            public boolean isSecure() {
                return sourceRequest.isSecure();
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String path) {
                return sourceRequest.getRequestDispatcher(path);
            }

            @Override
            public String getRealPath(String path) {
                return sourceRequest.getRealPath(path);
            }

            @Override
            public int getRemotePort() {
                return sourceRequest.getRemotePort();
            }

            @Override
            public String getLocalName() {
                return sourceRequest.getLocalName();
            }

            @Override
            public String getLocalAddr() {
                return sourceRequest.getLocalAddr();
            }

            @Override
            public int getLocalPort() {
                return sourceRequest.getLocalPort();
            }

            @Override
            public ServletContext getServletContext() {
                return sourceRequest.getServletContext();
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                return sourceRequest.startAsync();
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                return sourceRequest.startAsync(servletRequest, servletResponse);
            }

            @Override
            public boolean isAsyncStarted() {
                return sourceRequest.isAsyncStarted();
            }

            @Override
            public boolean isAsyncSupported() {
                return sourceRequest.isAsyncSupported();
            }

            @Override
            public AsyncContext getAsyncContext() {
                return sourceRequest.getAsyncContext();
            }

            @Override
            public DispatcherType getDispatcherType() {
                return sourceRequest.getDispatcherType();
            }
        }
    }
}
