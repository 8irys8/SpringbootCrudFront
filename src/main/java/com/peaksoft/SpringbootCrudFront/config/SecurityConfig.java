package com.peaksoft.SpringbootCrudFront.config;

import com.peaksoft.SpringbootCrudFront.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService, SuccessUserHandler successUserHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()

            .antMatchers(
                    "/**.js",
                    "/**.css"
                    ).permitAll()
            .antMatchers("/info.html").hasAuthority("ROLE_USER")
            .antMatchers("/users.html").hasAuthority("ROLE_ADMIN")
            .anyRequest().permitAll()

            .and()
            .formLogin()
            .permitAll()
            .successHandler(successUserHandler)
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)

            .permitAll();

        http.csrf().disable();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder); // конфигурация для прохождения аутентификации
    }
}
