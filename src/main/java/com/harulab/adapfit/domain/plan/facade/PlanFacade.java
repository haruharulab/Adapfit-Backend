package com.harulab.adapfit.domain.plan.facade;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.domain.repository.PlanRepository;
import com.harulab.adapfit.domain.plan.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PlanFacade {
    private final PlanRepository planRepository;

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan findByPlanId(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> PlanNotFoundException.EXCEPTION);
    }

    public void deletePlan(Plan plan) {
        planRepository.delete(plan);
    }

    public List<Plan> findAll() {
        return planRepository.findAll();
    }

}
