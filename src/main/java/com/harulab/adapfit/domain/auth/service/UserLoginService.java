package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.repository.UserRepository;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import com.harulab.adapfit.domain.user.exception.UserNotFoundException;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLoginService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthIdRepository authIdRepository;

    @Transactional
    public TokenResponseDto execute(LoginRequestDto req) {
        Authority authority = validateLoginInfo(req);
        authIdRepository.findByAuthId(req.getAuthId())
                .ifPresent(authIdRepository::delete);

        String accessToken = jwtProvider.generateAccessToken(req.getAuthId(), authority.name());
        String refreshToken = jwtProvider.generateRefreshToken(req.getAuthId(), authority.name());
        return new TokenResponseDto(accessToken, refreshToken, jwtProvider.getExpiredTime());
    }

    private Authority validateLoginInfo(LoginRequestDto req) {
        User user = userRepository.findByAuthId(req.getAuthId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.matchedPassword(passwordEncoder, user, req.getPassword());
        return user.getAuthority();
    }
}
