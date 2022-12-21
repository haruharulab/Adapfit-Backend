package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.jwt.JwtConstants;
import com.harulab.adapfit.global.security.jwt.JwtProperties;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    public String extractAuthIdFromToken(String bearer) {
        String token = jwtProvider.parseToken(bearer);
        return jwtAuth.getJws(token).getBody().get(JwtConstants.AUTH_ID.getMessage()).toString();
    }

    public String extractAuthorityFromToken(String bearer) {
        String token = jwtProvider.parseToken(bearer);
        return jwtAuth.getJws(token).getBody().get(JwtConstants.ROLE.getMessage()).toString();
    }

}
