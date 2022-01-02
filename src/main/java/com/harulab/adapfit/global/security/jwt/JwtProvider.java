package com.harulab.adapfit.global.security.jwt;

import com.harulab.adapfit.domain.auth.domain.AuthId;
import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.global.exception.ExpiredJwtException;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthIdRepository authIdRepository;
    private final JwtAuth jwtAuth;

    public String generateAccessToken(String authId, String role) {
        return jwtProperties.getPrefix()
                + JwtConstants.EMPTY.getMessage()
                + generateToken(
                authId,
                role,
                JwtConstants.ACCESS_KEY.getMessage(),
                jwtProperties.getAccessExp()
        );
    }

    public String generateRefreshToken(String authId, String role) {
        String refreshToken = jwtProperties.getPrefix() + JwtConstants.EMPTY.getMessage() + generateToken(authId, role, JwtConstants.REFRESH_KEY.getMessage(), jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .authId(authId)
                .token(refreshToken)
                .role(role)
                .ttl(jwtProperties.getRefreshExp())
                .build());

        return refreshToken;
    }

    private String generateToken(String authId, String role, String type, Long exp) {
        return Jwts.builder()
                .setSubject(authId)
                .setHeaderParam(JwtConstants.TYPE.getMessage(), type)
                .claim(JwtConstants.ROLE.getMessage(), role)
                .claim(JwtConstants.AUTH_ID.getMessage(), authId)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            String token = bearerToken.replace(jwtProperties.getPrefix(), "").trim();
            ifAuthIdIsPresentThrowException(token);
            return token;
        }
        return null;
    }

    private void ifAuthIdIsPresentThrowException(String bearerToken) {
        Claims body = jwtAuth.getJws(bearerToken).getBody();
        String tokenAuthId = body.get(JwtConstants.AUTH_ID.getMessage()).toString();

        Optional<AuthId> authId = authIdRepository.findByAuthId(tokenAuthId);

        if (authId.isPresent()) {
            throw ExpiredJwtException.EXCEPTION;
        }
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }

}
