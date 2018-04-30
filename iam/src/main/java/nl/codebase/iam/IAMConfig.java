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

/*
    private static class MyUserService implements UserDetailsService {

        private UserDao userDao;

        @Autowired
        public MyUserService(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {




            return new User("usr", "pwd", true, true,
                    true, true,
                    Arrays.asList(new SimpleGrantedAuthority("STANDARD_USER"),
                            new SimpleGrantedAuthority("ADMIN_USER")));


        }
    }*/

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

}
