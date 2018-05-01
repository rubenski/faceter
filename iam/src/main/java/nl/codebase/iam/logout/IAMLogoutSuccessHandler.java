package nl.codebase.iam.logout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static nl.codebase.faceter.common.FaceterConstants.PARAM_ACCESS_TOKEN;
import static nl.codebase.faceter.common.FaceterConstants.PARAM_REFRESH_TOKEN;

/**
 * Adds access and refresh tokens to logged_out_tokens table and removes cookies
 */
@Component
@Slf4j
public class IAMLogoutSuccessHandler implements LogoutHandler {

    private LogoutService logoutService;

    @Autowired
    public IAMLogoutSuccessHandler(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        response.addCookie(createImmediatelyExpiringCookie(PARAM_REFRESH_TOKEN));
        response.addCookie(createImmediatelyExpiringCookie(PARAM_ACCESS_TOKEN));

        Cookie[] cookies = request.getCookies();
        String test = "";
        // logoutService.logout();
    }

    private Cookie createImmediatelyExpiringCookie(String name) {
        log.info("Removing cookie {}", name);
        Cookie refreshTokenCookie = new Cookie(name, null);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0);
        return refreshTokenCookie;
    }
}
