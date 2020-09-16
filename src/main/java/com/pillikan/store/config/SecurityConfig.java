package com.pillikan.store.config;

import com.pillikan.store.jwt.AuthorizationFilter;
import com.pillikan.store.jwt.JwtProvider;
import com.pillikan.store.jwt.LoginFilter;
import com.pillikan.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private Long expirationInMillis;

    @Autowired
    private UserService userService;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secret, expirationInMillis, userService);
    }

    @Bean
    public AuthorizationFilter authorizationFilter() throws Exception {
        return new AuthorizationFilter(authenticationManager(), jwtProvider());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().addFilter(getLoginFilter()).addFilter(authorizationFilter());
    }

    private LoginFilter getLoginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(authenticationManager(), jwtProvider());
        loginFilter.setFilterProcessesUrl("/auth/login");
        return loginFilter;
    }
}
