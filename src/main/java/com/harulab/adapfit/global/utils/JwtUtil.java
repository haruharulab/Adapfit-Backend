package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.jwt.JwtConstants;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    @Transactional
    public String extractAuthorityFromToken(String bearer) {
        String token = jwtProvider.parseToken(bearer);
        return jwtAuth.getJws(token).getBody().get(JwtConstants.ROLE.getMessage()).toString();
    }

}
