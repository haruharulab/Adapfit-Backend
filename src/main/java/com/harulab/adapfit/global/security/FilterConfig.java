package com.harulab.adapfit.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harulab.adapfit.global.security.jwt.JwtFilter;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtProvider);
        new ExceptionFilter(objectMapper);
    }
}
