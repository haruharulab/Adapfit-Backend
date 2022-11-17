package com.harulab.adapfit.domain.super_admin.service;

import com.harulab.adapfit.domain.super_admin.domain.repository.SuperAdminRepository;
import com.harulab.adapfit.domain.super_admin.presentation.dto.req.SuperAdminCreateRequestDto;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SuperAdminService {

    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Transactional
    public void createRoot(SuperAdminCreateRequestDto req) {
        superAdminRepository.save(req.toEntity()).encodePassword(passwordEncoder);
    }

    public List<UserResponseDto> getUserList() {
        return userFacade.findAll()
                .stream()
                .map(user -> new UserResponseDto(user, user.getPlans()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAuthorityAdmin(Long id) {
        userFacade.findById(id).updateAuthority();
    }
}
