package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.domain.auth.exception.RefreshTokenNotFoundException;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.dto.TokenRefreshResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public TokenRefreshResponseDto execute(String refresh) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        return getNewTokens(redisRefreshToken);
    }

    private TokenRefreshResponseDto getNewTokens(RefreshToken redisRefreshToken) {
        String newAccessToken = jwtProvider.generateAccessToken(redisRefreshToken.getAuthId(), redisRefreshToken.getRole());

        return TokenRefreshResponseDto.builder()
                .accessToken(newAccessToken)
                .build();
    }

}
