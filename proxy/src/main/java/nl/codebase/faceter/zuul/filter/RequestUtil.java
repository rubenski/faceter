package nl.codebase.faceter.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class RequestUtil {

    public static HttpServletRequest getRequest() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequestWrapper wrapperRequest = (HttpServletRequestWrapper) context.getRequest();
        return wrapperRequest.getRequest();
    }

    public static String getParameter(String param) {
        return getRequest().getParameter(param);
    }

    public static String getCookieValue(String cookie) {
        Cookie[] cookies = getRequest().getCookies();
        if(cookies == null) {
            return null;
        }
        Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(c -> c.getName().equals(cookie)).findFirst();
        return optionalCookie.isPresent() ? optionalCookie.get().getValue() : null;
    }
}
