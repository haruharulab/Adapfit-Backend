package com.harulab.adapfit.domain.admin.service;

import com.harulab.adapfit.domain.admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.user.facade.AdminFacade;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final AdminFacade adminFacade;
    private final UserFacade userFacade;

    public void updateAccountInfo(UpdateAccountInfoRequestDto req) {
    }
}
