package com.harulab.adapfit.global.security.jwt.auth;

import com.harulab.adapfit.global.exception.InvalidJwtException;
import com.harulab.adapfit.global.security.auth.admin.AdminDetailsService;
import com.harulab.adapfit.global.security.auth.user.AuthDetailsService;
import com.harulab.adapfit.global.security.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuth {

    private final AuthDetailsService authDetailsService;
    private final AdminDetailsService adminDetailsService;
    private final JwtProperties jwtProperties;
    private static final String USER_ROLE = "USER";
    private static final String REFRESH_KEY = "refresh_token";


    public Authentication authentication(String token) {
        Claims body = getJws(token).getBody();
        if (!isNotRefreshToken(token))
            throw InvalidJwtException.EXCEPTION;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw com.harulab.adapfit.global.exception.ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    public boolean isNotRefreshToken(String token) {
        return !REFRESH_KEY.equals(getJws(token).getHeader().get("typ").toString());
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

}
