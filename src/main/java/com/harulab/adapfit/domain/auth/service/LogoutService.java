package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.root.domain.Root;
import com.harulab.adapfit.domain.root.facade.RootFacade;
import com.harulab.adapfit.domain.auth.domain.AuthId;
import com.harulab.adapfit.domain.auth.domain.RefreshToken;
import com.harulab.adapfit.domain.auth.domain.repository.AuthIdRepository;
import com.harulab.adapfit.domain.auth.domain.repository.RefreshTokenRepository;
import com.harulab.adapfit.domain.auth.exception.RefreshTokenNotFoundException;
import com.harulab.adapfit.domain.admin.facade.AdminFacade;
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

    private final RootFacade rootFacade;
    private final AdminFacade adminFacade;
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
            deleteUserRefreshToken(adminFacade.getCurrentAdmin());
        }
        if (Objects.equals(tokenRole, SUPER_ADMIN_ROLE.getMessage())){
            deleteSuperAdminRefreshToken(rootFacade.getCurrentRoot());
        }

        saveAuthId(accessToken);
    }

    private void saveAuthId(String accessToken) {
        String authId = jwtAuth.getJws(jwtProvider.parseToken(accessToken))
                .getBody().get(AUTH_ID.getMessage()).toString();
        authIdRepository.save(new AuthId().update(authId, jwtProperties.getRefreshExp() * 1000));
    }

    private void deleteUserRefreshToken(Admin admin) {
        RefreshToken refreshToken = refreshTokenRepository.findById(admin.getAuthId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        refreshTokenRepository.delete(refreshToken);
    }

    private void deleteSuperAdminRefreshToken(Root root) {
        RefreshToken refreshToken = refreshTokenRepository.findById(root.getAuthId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        refreshTokenRepository.delete(refreshToken);
    }
}