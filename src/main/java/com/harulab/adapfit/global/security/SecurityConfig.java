package com.harulab.adapfit.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harulab.adapfit.global.error.CustomAuthenticationEntryPoint;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;
    private final ObjectMapper objectMapper;
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";
    private static final String SUPER = "SUPER_ADMIN";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/admin").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/token").permitAll()

                // user
                .antMatchers(HttpMethod.PUT, "/user").hasRole(USER)
                .antMatchers(HttpMethod.DELETE, "/user").hasRole(USER)

                // admin
                .antMatchers(HttpMethod.PUT, "/admin").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/admin").hasRole(ADMIN)
                .antMatchers(HttpMethod.POST, "/plan/**").hasAnyRole(ADMIN)

                // super admin
                .antMatchers(HttpMethod.POST, "/super/auth/token").permitAll()
                .antMatchers(HttpMethod.GET, "/super/**").hasRole(SUPER)

                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and().apply(new FilterConfig(jwtProvider, jwtAuth));
        return http.build();
    }

}
