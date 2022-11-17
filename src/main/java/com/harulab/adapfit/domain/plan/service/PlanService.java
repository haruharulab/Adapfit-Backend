package com.harulab.adapfit.domain.plan.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.presentation.dto.req.CategoryCreateRequestDto;
import com.harulab.adapfit.domain.category.service.CategoryService;
import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.facade.PlanFacade;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlanService {

    private final PlanFacade planFacade;
    private final UserFacade userFacade;
    private final CategoryService categoryService;

    @Transactional
    public void createPlan(Long categoryId, PlanCreateRequestDto req) {
        Plan plan = planFacade.save(req.toEntity());
        plan.confirmWriter(userFacade.getCurrentUser());
        Category category = categoryService.savingInCategory(categoryId);
        plan.confirmCategory(category);
    }


    @Transactional
    public void updatePlan(Long planId, PlanUpdateRequestDto req) {
        planFacade.findByPlanId(planId).updatePlanInfo(req);
    }

    @Transactional
    public void deletePlan(Long planId) {
        Plan plan = planFacade.findByPlanId(planId);
        User user = userFacade.getCurrentUser();

        plan.isRightWriter(user);
        planFacade.deletePlan(plan);
    }
}
