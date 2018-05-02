package nl.codebase.faceter.zuul.filter;

import com.netflix.zuul.ZuulFilter;

public class TokenCheckerFilter extends ZuulFilter {

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
        // Filter if either token is present
        return RequestUtil.hasAccessToken() || RequestUtil.hasRefreshToken();
    }

    @Override
    public Object run() {

        if(RequestUtil.hasAccessToken()) {

        }

        return null;
    }
}
