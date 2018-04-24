package nl.codebase.iam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Main config for IAM. Contains beans that cause circular dependencies when included in other configs.
 */
@Configuration
public class IAMConfig {

    @Value("${security.signing-key}")
    private String signingKey;


    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserService();
    }

    private static class MyUserService implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return new User("usr", "pwd", true, true,
                    true, true,
                    Arrays.asList(new SimpleGrantedAuthority("STANDARD_USER"),
                            new SimpleGrantedAuthority("ADMIN_USER")));
        }
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

}
