package com.harulab.adapfit.domain.super_admin.service;

import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final UserFacade userFacade;

    public List<UserResponseDto> getUserList() {
        return userFacade.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAuthorityAdmin(Long id) {
        userFacade.findById(id).updateAuthority();
    }
}
