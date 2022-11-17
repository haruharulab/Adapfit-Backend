package com.harulab.adapfit.domain.root.facade;

import com.harulab.adapfit.domain.root.domain.SuperAdmin;
import com.harulab.adapfit.domain.root.domain.repository.SuperAdminRepository;
import com.harulab.adapfit.global.exception.AdminNotFoundException;
import com.harulab.adapfit.global.utils.SuperAdminUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SuperAdminFacade {

    private final SuperAdminRepository superAdminRepository;

    public SuperAdmin getCurrentAdmin() {
        return superAdminRepository.findByAuthId(SuperAdminUtil.getCurrentUser().getUsername())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
