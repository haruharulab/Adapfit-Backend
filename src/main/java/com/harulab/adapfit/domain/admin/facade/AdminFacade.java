package com.harulab.adapfit.domain.admin.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.repository.AdminRepository;
import com.harulab.adapfit.domain.admin.exception.AdminNotFoundException;
import com.harulab.adapfit.global.socket.property.SocketProperty;
import com.harulab.adapfit.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final AdminRepository adminRepository;

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getCurrentUser() {
        return adminRepository.findById(SecurityUtil.getCurrentUser().getAdmin().getId())
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

    public Admin findUserByClient(SocketIOClient client) {
        return adminRepository.findByAuthId(client.get(SocketProperty.USER_KEY))
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
