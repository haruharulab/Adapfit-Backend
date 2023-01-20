package com.harulab.adapfit.domain.log.global.utils;

import com.harulab.adapfit.domain.log.global.security.jwt.JwtConstants;
import com.harulab.adapfit.domain.log.global.security.jwt.JwtProvider;
import com.harulab.adapfit.domain.log.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    public String extractAuthIdFromToken(String bearer) {
        String token = jwtProvider.parseToken(bearer);
        return jwtAuth.getJws(token).getBody().get(JwtConstants.AUTH_ID.getMessage()).toString();
    }

    @Transactional
    public String extractAuthorityFromToken(String bearer) {
        String token = jwtProvider.parseToken(bearer);
        return jwtAuth.getJws(token).getBody().get(JwtConstants.ROLE.getMessage()).toString();
    }

}
