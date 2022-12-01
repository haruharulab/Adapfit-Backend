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

                // admin
                .antMatchers(HttpMethod.PUT, "/admin").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/admin").hasRole(ADMIN)
                .antMatchers(HttpMethod.POST, "/plan/**").hasAnyRole(ADMIN)
                .antMatchers(HttpMethod.POST, "/category").hasAnyRole(ADMIN, SUPER)
                .antMatchers(HttpMethod.PUT, "/category/{categoryId}").hasAnyRole(ADMIN, SUPER)
                .antMatchers(HttpMethod.DELETE, "/category/{categoryId}").hasAnyRole(ADMIN, SUPER)
                .antMatchers(HttpMethod.PUT, "/user/pw").hasAnyRole(ADMIN, SUPER)

                // super admin
                .antMatchers("/super/auth/token").permitAll()
                .antMatchers( "/super/**").hasRole(SUPER)
                .antMatchers(HttpMethod.POST, "/recruitment").hasAnyRole(SUPER)
                .antMatchers(HttpMethod.PUT, "/recruitment/{recruitId}").hasAnyRole(SUPER)
                .antMatchers(HttpMethod.DELETE, "/recruitment/{recruitId}").hasAnyRole(SUPER)

                // socket.io
                .antMatchers(HttpMethod.GET, "/socket.io").authenticated()

                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and().apply(new FilterConfig(jwtProvider, jwtAuth));
        return http.build();
    }

}
