package com.harulab.adapfit.domain.admin.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.facade.AdminFacade;
import com.harulab.adapfit.domain.admin.presentation.dto.req.JoinAdminRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final AdminFacade adminFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinAdminRequestDto req) {
        adminFacade.save(req.toEntity()).encodePassword(passwordEncoder);
    }

    @Transactional
    public void updateAccountInfo(UpdateAccountInfoRequestDto req) {
        Admin admin = adminFacade.getCurrentAdmin();
        admin.updateInfo();
    }
}
