package com.harulab.adapfit.global.security.jwt;

import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public TokenResponseDto generateToken(String authId, String role) {
        String accessToken = generateToken(authId, role, jwtProperties.getAccessExp());
        String refreshToken = generateToken(authId, role, jwtProperties.getRefreshExp());
        return new TokenResponseDto(accessToken, refreshToken);
    }

    private String generateToken(String authId, String role, Long exp) {
        return Jwts.builder()
                .setSubject(authId)
                .claim("ROLE", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(
                        new Date(System.currentTimeMillis() + exp * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }


}
