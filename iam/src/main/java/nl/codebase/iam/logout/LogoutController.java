package nl.codebase.iam.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

    private LogoutService logoutService;

    @Autowired
    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @RequestMapping(path = "/logout3", method = RequestMethod.GET)
    public void logout(@CookieValue(value = "access_token", required = false) String accessToken,
                       @CookieValue(value = "refresh_token", required = false) String refreshToken) {
        logoutService.logout(accessToken, refreshToken);
    }


    @RequestMapping(path = "/logout2", method = RequestMethod.GET)
    public void logout2() {
        String test = "";
    }
}
