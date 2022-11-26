package com.harulab.adapfit.global.utils.token;

import com.harulab.adapfit.global.security.jwt.JwtConstants;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    public String ExtractAuthIdFromToken(String bearer) {
        String token = jwtProvider.parseToken(bearer);
        return jwtAuth.getJws(token).getBody().get(JwtConstants.AUTH_ID.getMessage()).toString();
    }
}
