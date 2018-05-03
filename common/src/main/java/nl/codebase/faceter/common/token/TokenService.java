package nl.codebase.faceter.common.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
public class TokenService {

    @Value("${security.signing-key}")
    private String signingKey;

    private TokenDao tokenDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TokenService(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Transactional
    public void logout(String type, String token) {

        if (type == null || token == null) {
            throw new IllegalArgumentException("type and token cannot be null");
        }

        JwtClaims jwtClaims = getJwtClaims(token);
        int exp = jwtClaims.getExp();
        tokenDao.insertLoggedOutToken(token, exp);

    }

    private JwtClaims getJwtClaims(String token) {
        Jwt jwt = JwtHelper.decodeAndVerify(token, new MacSigner(signingKey));
        String claims = jwt.getClaims();

        try {
            return mapper.readValue(claims, JwtClaims.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not deserialize JWT claims", e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    private static class JwtClaims {
        private int exp;
    }
}
