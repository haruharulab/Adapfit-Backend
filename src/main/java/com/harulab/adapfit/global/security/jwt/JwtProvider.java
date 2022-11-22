package com.harulab.adapfit.global.security.jwt;

import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.RefreshTokenRepository;
import com.harulab.adapfit.global.exception.InvalidJwtException;
import com.harulab.adapfit.global.security.auth.AuthDetailsService;
import com.harulab.adapfit.global.security.auth.admin.AdminDetailsService;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AdminDetailsService adminDetailsService;
    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";
    private static final String USER_ROLE = "USER";

    public TokenResponseDto generateToken(String id, String role) {
        String accessToken = generateToken(id, role, ACCESS_KEY, jwtProperties.getAccessExp());
        String refreshToken = generateToken(id, role, REFRESH_KEY, jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .id(id)
                .token(refreshToken)
                .ttl(jwtProperties.getRefreshExp() * 1000)
                .build());

        return new TokenResponseDto(accessToken, refreshToken, getExpiredTime());
    }

    private String generateToken(String id, String role, String type, Long exp) {
        return Jwts.builder()
                .setSubject(id)
                .setHeaderParam("typ", type)
                .claim("role", role)
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
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())){
            return bearerToken.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getDetails(Claims body) {
        if (USER_ROLE.equals(body.get("role").toString())) {
            return authDetailsService
                    .loadUserByUsername(body.getSubject());
        } else {
            return adminDetailsService
                    .loadUserByUsername(body.getSubject());
        }
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }

    private Claims getTokenBody(String token) {

        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw com.harulab.adapfit.global.exception.ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

}
