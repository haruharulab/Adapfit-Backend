package com.harulab.adapfit.domain.admin.facade;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.AdminRepository;
import com.harulab.adapfit.global.exception.AdminNotFoundException;
import com.harulab.adapfit.global.utils.AdminUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminFacade {

    private final AdminRepository adminRepository;

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getCurrentAdmin() {
        return adminRepository.findByAuthId(AdminUtil.getCurrentUser().getUsername())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    public List<Admin> findByWaitingList() {
        return adminRepository.findByWaitingList();
    }
}
