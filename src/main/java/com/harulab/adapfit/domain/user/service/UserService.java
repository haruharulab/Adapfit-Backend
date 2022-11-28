package com.harulab.adapfit.domain.user.service;

import com.harulab.adapfit.domain.super_admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UserService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto getMyInfo() {
        User user = userFacade.getCurrentUser();
        return new UserResponseDto(user, user.getPlans());
    }

    @Transactional
    public void join(UserRequestDto req) {
        userFacade.save(req.toEntity()).encodePassword(passwordEncoder);
    }

    @Transactional
    public void updateAccountInfo(UpdateAccountInfoRequestDto req) {
        User user = userFacade.getCurrentUser();
        user.updateInfo(req);
    }
}
