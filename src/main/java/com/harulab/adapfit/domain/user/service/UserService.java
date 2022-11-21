package com.harulab.adapfit.domain.user.service;

import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFacade userFacade;

    public void join(UserRequestDto req) {
        userFacade.save(req.toEntity());
    }
}
