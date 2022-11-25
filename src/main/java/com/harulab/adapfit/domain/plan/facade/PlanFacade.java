package com.harulab.adapfit.domain.plan.facade;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.domain.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlanFacade {

    private final PlanRepository planRepository;

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }
}
