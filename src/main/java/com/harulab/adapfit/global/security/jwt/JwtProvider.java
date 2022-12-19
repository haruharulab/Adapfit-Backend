package com.harulab.adapfit.global.security.jwt;

import com.harulab.adapfit.domain.auth.domain.AuthId;
import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.global.exception.ExpiredJwtException;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import static com.harulab.adapfit.global.security.jwt.JwtConstants.*;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthIdRepository authIdRepository;
    private final JwtAuth jwtAuth;

    public TokenResponseDto generateToken(String authId, String role) {
        String accessToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
        String refreshToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, REFRESH_KEY.getMessage(), jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .id(authId)
                .token(refreshToken)
                .role(role)
                .ttl(jwtProperties.getRefreshExp() * 1000)
                .build());

        return new TokenResponseDto(accessToken, refreshToken, getExpiredTime());
    }

    private String generateToken(String authId, String role, String type, Long exp) {
        return Jwts.builder()
                .setSubject(authId)
                .setHeaderParam(TYPE.getMessage(), type)
                .claim(ROLE.getMessage(), role)
                .claim(AUTH_ID.getMessage(), authId)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(
                        new Date(System.currentTimeMillis() + exp * 1000)
                )
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
        String tokenAuthId = body.get(AUTH_ID.getMessage()).toString();

        Optional<AuthId> authId = authIdRepository.findByAuthId(tokenAuthId);

        if (authId.isPresent()) {
            throw ExpiredJwtException.EXCEPTION;
        }
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }

}
