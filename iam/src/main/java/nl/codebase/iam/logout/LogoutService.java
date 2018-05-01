package nl.codebase.iam.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogoutService {

    private LogoutDao logoutDao;

    @Autowired
    public LogoutService(LogoutDao logoutDao) {
        this.logoutDao = logoutDao;
    }

    @Transactional
    public void logout(String accessToken, String refreshToken) {

        if(accessToken != null) {
            logoutDao.insertLoggedOutToken(accessToken);
        }

        if(refreshToken != null) {
            logoutDao.insertLoggedOutToken(refreshToken);
        }
    }
}
