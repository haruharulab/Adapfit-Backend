package com.harulab.adapfit.domain.user.service;

import com.harulab.adapfit.domain.super_admin.domain.SuperAdmin;
import com.harulab.adapfit.domain.super_admin.facade.SuperAdminFacade;
import com.harulab.adapfit.domain.auth.domain.AuthId;
import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.domain.auth.exception.RefreshTokenNotFoundException;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LogoutService {

    private final SuperAdminFacade superAdminFacade;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthIdRepository authIdRepository;
    private final JwtProvider jwtProvider;
    private final JwtAuth jwtAuth;

    @Transactional
    public void execute(String accessToken) {
        SuperAdmin superAdmin = superAdminFacade.getCurrentAdmin();
        String token = jwtProvider.parseToken(accessToken);
        String authId = jwtAuth.getJws(token).getBody().get("authId").toString();

        authIdRepository.save(new AuthId().update(authId));
        RefreshToken refreshToken = refreshTokenRepository.findById(superAdmin.getAuthId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}