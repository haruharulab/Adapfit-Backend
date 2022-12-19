package com.harulab.adapfit.domain.root.service;

import com.harulab.adapfit.domain.root.domain.repository.SuperAdminRepository;
import com.harulab.adapfit.domain.root.facade.SuperAdminFacade;
import com.harulab.adapfit.domain.root.presentation.dto.req.SuperAdminCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.res.SuperAdminResponseDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import com.harulab.adapfit.domain.user.presentation.dto.res.AdminCreateResponseDto;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SuperAdminService {

    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final SuperAdminFacade superAdminFacade;

    public SuperAdminResponseDto getInfo() {
        return new SuperAdminResponseDto(superAdminFacade.getCurrentAdmin());
    }

    @Transactional
    public void createRoot(SuperAdminCreateRequestDto req) {
        superAdminRepository.save(req.toEntity()).encodePassword(passwordEncoder);
    }

    @Transactional
    public AdminCreateResponseDto createAdmin() {
        UserRequestDto userRequestDto = new UserRequestDto();
        User user = userFacade.save(userRequestDto.toEntity());
        user.encodePassword(passwordEncoder);
        return new AdminCreateResponseDto(userRequestDto.getAuthId());
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
