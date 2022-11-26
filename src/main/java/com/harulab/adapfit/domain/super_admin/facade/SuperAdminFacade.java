package com.harulab.adapfit.domain.super_admin.facade;

import com.harulab.adapfit.domain.super_admin.domain.SuperAdmin;
import com.harulab.adapfit.domain.super_admin.domain.SuperAdminRepository;
import com.harulab.adapfit.global.exception.AdminNotFoundException;
import com.harulab.adapfit.global.utils.AdminUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SuperAdminFacade {

    private final SuperAdminRepository superAdminRepository;

    public SuperAdmin getCurrentAdmin() {
        return superAdminRepository.findByAuthId(AdminUtil.getCurrentUser().getUsername())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
