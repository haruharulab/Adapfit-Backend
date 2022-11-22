package com.harulab.adapfit.domain.user.service;

import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserRequestDto req) {
        userFacade.save(req.toEntity()).encodePassword(passwordEncoder);
    }
}
