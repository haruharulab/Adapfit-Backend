package com.harulab.adapfit.domain.plan.service;

import com.harulab.adapfit.domain.plan.facade.PlanFacade;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlanService {

    private final PlanFacade planFacade;
    private final UserFacade userFacade;

    @Transactional
    public void createPlan(PlanCreateRequestDto req) {
        planFacade.save(req.toEntity())
                .saveUser(userFacade.getCurrentUser());
    }
}
