package com.harulab.adapfit.global.security.jwt.auth;

import com.harulab.adapfit.global.exception.ExpiredJwtException;
import com.harulab.adapfit.global.exception.InvalidJwtException;
import com.harulab.adapfit.global.security.auth.super_admin.SuperAdminDetailsService;
import com.harulab.adapfit.global.security.auth.user.AuthDetailsService;
import com.harulab.adapfit.global.security.jwt.JwtProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.harulab.adapfit.global.security.jwt.JwtConstants.*;

@RequiredArgsConstructor
@Component
public class JwtAuth {

    private final AuthDetailsService authDetailsService;
    private final SuperAdminDetailsService superAdminDetailsService;
    private final JwtProperties jwtProperties;

    public Authentication authentication(String token) {
        Claims body = getJws(token).getBody();

        if (!isNotRefreshToken(token)){
            throw InvalidJwtException.EXCEPTION;
        }

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    public boolean isNotRefreshToken(String token) {
        return !REFRESH_KEY.getMessage()
                .equals(getJws(token).getHeader().get(TYPE.getMessage()).toString());
    }

    private UserDetails getDetails(Claims body) {
        if (USER_ROLE.getMessage().equals(body.get(ROLE.getMessage()).toString())
    || ADMIN_ROLE.getMessage().equals(body.get(ROLE.getMessage()).toString())) {
            return authDetailsService
                    .loadUserByUsername(body.getSubject());
        }
        return superAdminDetailsService
                .loadUserByUsername(body.getSubject());
    }

}
