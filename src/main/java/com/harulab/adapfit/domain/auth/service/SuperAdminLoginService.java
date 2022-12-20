package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.root.domain.SuperAdmin;
import com.harulab.adapfit.domain.root.domain.repository.SuperAdminRepository;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.user.domain.type.Authority;
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

    private final SuperAdminRepository superAdminRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthIdRepository authIdRepository;

    @Transactional
    public TokenResponseDto execute(LoginRequestDto req) {
        validateLoginInfo(req);
        authIdRepository.findByAuthId(req.getAuthId())
                        .ifPresent(authIdRepository::delete);
        String accessToken = jwtProvider.generateAccessToken(req.getAuthId(), Authority.SUPER_ADMIN.name());
        String refreshToken = jwtProvider.generateRefreshToken(req.getAuthId(), Authority.SUPER_ADMIN.name());

        return new TokenResponseDto(accessToken, refreshToken, jwtProvider.getExpiredTime());
    }

    private void validateLoginInfo(LoginRequestDto req) {
        SuperAdmin superAdmin = superAdminRepository.findByAuthId(req.getAuthId())
                .orElseThrow(() -> new AdapfitException(ErrorCode.ADMIN_NOT_FOUND));

        superAdmin.matchedPassword(passwordEncoder, superAdmin, req.getPassword());
    }
}
