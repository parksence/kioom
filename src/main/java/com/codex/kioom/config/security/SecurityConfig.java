package com.codex.kioom.config.security;

import com.codex.kioom.config.CustomAuthFailureHandler;
import com.codex.kioom.config.CustomAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/account_proc").authenticated()
                .antMatchers("/update_proc").authenticated()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/data/**").authenticated()
                .antMatchers("/monitoring/**").authenticated()
                .antMatchers("/hospital/**").authenticated()
                .antMatchers("/patient/**").authenticated()
                .antMatchers("/myData/**").authenticated()
                .antMatchers("/api/uploadExcel").authenticated()
                .anyRequest().permitAll()
                .and()
        .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .successHandler(customAuthSuccessHandler)
                .failureHandler(customAuthFailureHandler)
                .and()
        .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .permitAll();
        http
            .rememberMe()
            .key(UUID.randomUUID().toString())
            .rememberMeParameter("remember-me")
            .tokenValiditySeconds(86400*30)
            .alwaysRemember(true)
            .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(
                    "/resources/**",
                    "/css/**",
                    "/fonts/**",
                    "/js/**",
                    "/static/**",
                    "/module/**",
                    "/webjars/**",
                    "/less/**",
                    "/img/**"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
