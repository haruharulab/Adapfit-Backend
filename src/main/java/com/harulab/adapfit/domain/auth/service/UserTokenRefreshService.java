package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.domain.auth.exception.RefreshTokenNotFoundException;
import com.harulab.adapfit.global.security.jwt.JwtProperties;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import com.harulab.adapfit.global.utils.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserTokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;
    private final JwtUtil jwtUtil;

    public TokenResponseDto execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findById(jwtUtil.ExtractAuthIdFromToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        return getNewTokens(redisRefreshToken);
    }

    private TokenResponseDto getNewTokens(RefreshToken redisRefreshToken) {
        String newRefreshToken = jwtProvider.generateToken(redisRefreshToken.getId(), "USER").getRefreshToken();
        redisRefreshToken.update(newRefreshToken, jwtProperties.getRefreshExp());

        String newAccessToken = jwtProvider.generateToken(redisRefreshToken.getId(), "USER").getAccessToken();
        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtProvider.getExpiredTime())
                .build();
    }
}
