package com.harulab.adapfit.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import com.harulab.adapfit.global.error.CustomAuthenticationEntryPoint;
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

    private static final String HUMAN_RESOURCES_ADMIN = "HUMAN_RESOURCES_ADMIN";
    private static final String CENTER_ADMIN = "CENTER_ADMIN";
    private static final String ADAPFIT_ADMIN = "ADAPFIT_ADMIN";
    private static final String ROOT = "ROOT";

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

                // ADMIN
                .antMatchers(HttpMethod.GET, "/plan").permitAll()
                .antMatchers(HttpMethod.GET, "/plan/{planId}").permitAll()
                .antMatchers(HttpMethod.POST, "/plan/**").hasAnyRole(CENTER_ADMIN, ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.POST, "/category").hasAnyRole(ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.PUT, "/category/{categoryId}").hasAnyRole(ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.DELETE, "/category/{categoryId}").hasAnyRole(ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.PUT, "/user/pw").hasAnyRole(ADAPFIT_ADMIN, HUMAN_RESOURCES_ADMIN, CENTER_ADMIN, ROOT)

                .antMatchers(HttpMethod.POST, "/recruitment").hasAnyRole(HUMAN_RESOURCES_ADMIN, ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.PUT, "/recruitment/{recruitId}").hasAnyRole(HUMAN_RESOURCES_ADMIN, ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.DELETE, "/recruitment/{recruitId}").hasAnyRole(HUMAN_RESOURCES_ADMIN, ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.POST, "/banner").hasAnyRole(ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.GET, "/resume/**").hasAnyRole(HUMAN_RESOURCES_ADMIN, ADAPFIT_ADMIN, ROOT)
                .antMatchers("/position/**").hasAnyRole(HUMAN_RESOURCES_ADMIN, ADAPFIT_ADMIN, ROOT)
                .antMatchers(HttpMethod.POST, "/notice").hasAnyRole(ADAPFIT_ADMIN, ROOT)

                // ROOT
                .antMatchers(HttpMethod.POST, "/super").permitAll()
                .antMatchers("/super/auth/token").permitAll()
                .antMatchers( "/super/**").hasRole(ROOT)
                .antMatchers(HttpMethod.PUT, "/notice/{noticeId}").hasRole(ROOT)
                .antMatchers(HttpMethod.GET, "/log/**").hasRole(ROOT)

                // socket.io
                .antMatchers(HttpMethod.GET, "/socket.io").authenticated()

                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .and().apply(new FilterConfig(jwtProvider, jwtAuth));
        return http.build();
    }

}
