package com.harulab.adapfit.domain.admin.facade;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.repository.AdminRepository;
import com.harulab.adapfit.domain.admin.exception.AdminNotFoundException;
import com.harulab.adapfit.domain.log.global.utils.AdminUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final AdminRepository adminRepository;

    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getCurrentAdmin() {
        return adminRepository.findById(AdminUtil.getCurrentUser().getAdmin().getId())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    public Admin findByAuthId(String authId) {
        return adminRepository.findByAuthId(authId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    public void delete(Long adminId) {
        adminRepository.deleteById(adminId);
    }

}
