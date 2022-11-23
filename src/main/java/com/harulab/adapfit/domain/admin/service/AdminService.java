package com.harulab.adapfit.domain.admin.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.facade.AdminFacade;
import com.harulab.adapfit.domain.admin.presentation.dto.req.JoinAdminRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<AdminResponseDto> getJoinWaitingList() {
        return adminFacade.findByWaitingList()
                .stream()
                .map(AdminResponseDto::new)
                .collect(Collectors.toList());
    }
}
