package com.harulab.adapfit.domain.log.global.security;

import com.harulab.adapfit.domain.log.global.security.jwt.JwtProvider;
import com.harulab.adapfit.domain.log.global.security.jwt.auth.JwtAuth;
import com.harulab.adapfit.domain.log.global.error.ExceptionFilter;
import com.harulab.adapfit.domain.log.global.security.jwt.auth.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    @Override
    public void configure(HttpSecurity builder) {
        JwtFilter jwtFilter = new JwtFilter(jwtProvider, jwtAuth);
        ExceptionFilter globalExceptionFilter = new ExceptionFilter();
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(globalExceptionFilter, JwtFilter.class);
    }
}
