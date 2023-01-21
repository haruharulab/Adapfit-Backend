package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.root.domain.Root;
import com.harulab.adapfit.domain.root.domain.repository.RootRepository;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.admin.domain.type.Authority;
import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SuperAdminLoginService {

    private final RootRepository rootRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthIdRepository authIdRepository;

    @Transactional
    public TokenResponseDto execute(LoginRequestDto req) {
        validateLoginInfo(req);
        authIdRepository.findByAuthId(req.getAuthId())
                        .ifPresent(authIdRepository::delete);
        String accessToken = jwtProvider.generateAccessToken(req.getAuthId(), Authority.ROOT.name());
        String refreshToken = jwtProvider.generateRefreshToken(req.getAuthId(), Authority.ROOT.name());

        return new TokenResponseDto(accessToken, refreshToken, jwtProvider.getExpiredTime());
    }

    private void validateLoginInfo(LoginRequestDto req) {
        Root root = rootRepository.findByAuthId(req.getAuthId())
                .orElseThrow(() -> new AdapfitException(ErrorCode.ADMIN_NOT_FOUND));

        root.matchedPassword(passwordEncoder, root, req.getPassword());
    }
}
