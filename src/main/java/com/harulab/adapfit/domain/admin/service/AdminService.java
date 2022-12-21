package com.harulab.adapfit.domain.admin.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.admin.exception.PasswordNotMatchException;
import com.harulab.adapfit.domain.admin.facade.AdminFacade;
import com.harulab.adapfit.domain.admin.presentation.dto.req.PasswordRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.req.AdminRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class AdminService {

    private final AdminFacade adminFacade;
    private final PasswordEncoder passwordEncoder;

    public AdminResponseDto getMyInfo() {
        Admin admin = adminFacade.getCurrentUser();
        return new AdminResponseDto(admin);
    }

    @Transactional
    public void join(AdminRequestDto req) {
        adminFacade.save(req.toEntity()).encodePassword(passwordEncoder);
    }

    @Transactional
    public void updateAccountInfo(UpdateAccountInfoRequestDto req) {
        Admin admin = adminFacade.getCurrentUser();
        admin.updateInfo(req);
    }

    @Transactional
    public void updatePassword(PasswordRequestDto req) {
        if (!Objects.equals(req.getNewPassword(), req.getValidatePassword())) {
            throw new PasswordNotMatchException();
        }
        Admin admin = adminFacade.getCurrentUser();
        admin.updatePassword(passwordEncoder, req.getNewPassword());
    }

    @Transactional
    public void updatePassword(Long adminId, PasswordRequestDto req) {
        if (!Objects.equals(req.getNewPassword(), req.getValidatePassword())) {
            throw new PasswordNotMatchException();
        }
        Admin admin = adminFacade.findById(adminId);
        admin.updatePassword(passwordEncoder, req.getNewPassword());
    }

    public AdminResponseDto getDetail(Long adminId) {
        Admin admin = adminFacade.findById(adminId);
        return new AdminResponseDto(admin);
    }

    @Transactional
    public void remove(Long adminId) {
        adminFacade.delete(adminId);
    }
}
