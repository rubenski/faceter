package nl.codebase.faceter.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import java.io.InputStream;
import java.util.Map;
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

        Optional<StandardMultipartHttpServletRequest> multiPartRequestOptional = getMultiPartRequest();
        if(multiPartRequestOptional.isPresent()) {
            MultipartHttpServletRequest multipartRequest = multiPartRequestOptional.get();
            Map<String, String[]> parameterMap = multipartRequest.getParameterMap();
            if(parameterMap.containsKey(GRANT_TYPE)) {
                String grantTypeValue = parameterMap.get(GRANT_TYPE)[0];
                if(grantTypeValue != null && grantTypeValue.equals(REFRESH_TOKEN)) {
                    return true;
                }
            }

        }

        return false;

    }

    private Optional<StandardMultipartHttpServletRequest> getMultiPartRequest() {
        RequestContext context = RequestContext.getCurrentContext();
        InputStream stream = (InputStream) context.get("requestEntity");
        if (stream == null) {
            HttpServletRequestWrapper request = (HttpServletRequestWrapper) context.getRequest();
            if (request.getRequest() instanceof StandardMultipartHttpServletRequest) {
                return Optional.of((StandardMultipartHttpServletRequest) request.getRequest());
            }
        }
        return Optional.empty();
    }

    @Override
    public Object run() {
        Optional<StandardMultipartHttpServletRequest> multiPartRequestOptional = getMultiPartRequest();
        RequestContext context = RequestContext.getCurrentContext();
        if(multiPartRequestOptional.isPresent()) {
            // get the refresh token from cookie here and set it in the body

            Cookie[] cookies = context.getRequest().getCookies();
            // byte[] bytes = body.getBytes("UTF-8");

            /*context.setRequest(new HttpServletRequestWrapper(context.getRequest()) {
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
            });*/
        }
        return null;
    }
}
