package nl.codebase.iam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Main config for IAM. Contains beans that cause circular dependencies when included in other configs.
 */
@Configuration
public class IAMConfig {

    @Value("${security.signing-key}")
    private String signingKey;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }
}
