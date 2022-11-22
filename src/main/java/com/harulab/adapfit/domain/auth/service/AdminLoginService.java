package com.harulab.adapfit.domain.auth.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.AdminRepository;
import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.UserRepository;
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
public class AdminLoginService {

    private final AdminRepository adminRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponseDto execute(LoginRequestDto req) {
        validateLoginInfo(req);
        return jwtProvider.generateToken(req.getAuthId(), "ADMIN");
    }

    private void validateLoginInfo(LoginRequestDto req) {
        Admin admin = adminRepository.findByAuthId(req.getAuthId())
                .orElseThrow(() -> new AdapfitException(ErrorCode.ADMIN_NOT_FOUND));

        admin.matchedPassword(passwordEncoder, admin, req.getPassword());
    }
}
