package com.harulab.adapfit.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harulab.adapfit.global.error.ExceptionFilter;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import com.harulab.adapfit.global.security.jwt.auth.JwtFilter;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;
    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity builder) {
        JwtFilter jwtFilter = new JwtFilter(jwtProvider, jwtAuth);
        ExceptionFilter exceptionFilter = new ExceptionFilter(objectMapper);
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(exceptionFilter, JwtFilter.class);
    }
}
