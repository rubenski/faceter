package nl.codebase.faceter.forms;

import nl.codebase.faceter.common.CsrfTokenResponseFilter;
import nl.codebase.faceter.forms.filter.TokenMoverFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Value("${security.security-realm}")
    private String securityRealm;

    @Value("${security.signing-key}")
    private String signingKey;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new ShaPasswordEncoder(encodingStrength));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName(securityRealm)
                .and()
                .csrf()
                .disable();

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> null;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    /**
     * Moves the JWT token from the incoming cookie to the request header
     * @return
     */
    @Bean
    public FilterRegistrationBean tokenMoverFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TokenMoverFilter tokenMoverFilter = new TokenMoverFilter();
        registrationBean.setFilter(tokenMoverFilter);
        // DO NOT TOUCH THE ORDER OF THIS FILTER. IT WILL SCREW THINGS UP ROYALLY FOR YOU. TOKEN MUST BE MOVED TO
        // HEADER BEFORE THE SPRING SECURITY CHAIN STARTS.
        registrationBean.setOrder(-100);
        return registrationBean;
    }

    /**
     * Returns a csrf token in every response
     * @return
     */
    @Bean
    public FilterRegistrationBean csrfFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();;
        registrationBean.setFilter(new CsrfTokenResponseFilter());
        //registrationBean.setOrder(1);
        return registrationBean;
    }
}
