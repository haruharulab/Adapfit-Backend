package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.root.domain.SuperAdmin;
import com.harulab.adapfit.domain.root.facade.SuperAdminFacade;
import com.harulab.adapfit.domain.auth.domain.AuthId;
import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.domain.auth.exception.RefreshTokenNotFoundException;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.global.security.jwt.JwtProperties;
import com.harulab.adapfit.global.security.jwt.JwtProvider;
import com.harulab.adapfit.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.harulab.adapfit.global.security.jwt.JwtConstants.*;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class LogoutService {

    private final SuperAdminFacade superAdminFacade;
    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthIdRepository authIdRepository;
    private final JwtAuth jwtAuth;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public void execute(String accessToken) {
        String tokenRole = jwtAuth.getJws(jwtProvider.parseToken(accessToken))
                .getBody().get(ROLE.getMessage()).toString();
        if (Objects.equals(tokenRole, ADMIN_ROLE.getMessage())) {
            deleteUserRefreshToken(userFacade.getCurrentUser());
        }
        if (Objects.equals(tokenRole, SUPER_ADMIN_ROLE.getMessage())){
            deleteSuperAdminRefreshToken(superAdminFacade.getCurrentAdmin());
        }

        saveAuthId(accessToken);
    }

    private void saveAuthId(String accessToken) {
        String authId = jwtAuth.getJws(jwtProvider.parseToken(accessToken))
                .getBody().get(AUTH_ID.getMessage()).toString();
        authIdRepository.save(new AuthId().update(authId, jwtProperties.getRefreshExp() * 1000));
    }

    private void deleteUserRefreshToken(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findById(user.getAuthId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        refreshTokenRepository.delete(refreshToken);
    }

    private void deleteSuperAdminRefreshToken(SuperAdmin superAdmin) {
        RefreshToken refreshToken = refreshTokenRepository.findById(superAdmin.getAuthId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        refreshTokenRepository.delete(refreshToken);
    }
}