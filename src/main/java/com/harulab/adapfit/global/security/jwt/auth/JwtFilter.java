package com.harulab.adapfit.global.security.jwt.auth;

import com.harulab.adapfit.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearer = jwtProvider.resolveToken(request);
        isBearerNotNullSetContextHolderToAuthentication(bearer);
        filterChain.doFilter(request, response);
    }

    private void isBearerNotNullSetContextHolderToAuthentication(String bearer) {
        if (bearer != null) {
            Authentication authentication = jwtAuth.authentication(bearer);
            System.out.println("authentication : " + authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
