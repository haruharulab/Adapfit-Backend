package com.harulab.adapfit.domain.plan.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.service.CategoryService;
import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.facade.PlanFacade;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.facade.UserFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class PlanService {

    private final PlanFacade planFacade;
    private final UserFacade userFacade;
    private final CategoryService categoryService;

    @Transactional
    public void createPlan(PlanRequestDto req) throws IOException {
        S3FileResponseDto fileDto = planFacade.savePlanFile(req);

        Plan plan = req.toEntity(fileDto);
        plan.confirmWriter(userFacade.getCurrentUser());

        Category category = categoryService.savingInCategory(req.getCategoryId());
        plan.confirmCategory(category);

        planFacade.save(plan);
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

    public PlanResponseDto getPlanDetail(Long planId) {
        Plan plan = planFacade.findByPlanId(planId);
        return new PlanResponseDto(plan, plan.getCategory());
    }

    public List<PlanResponseDto> getAllPlan() {
        return planFacade.findAll()
                .stream()
                .map(plan -> new PlanResponseDto(plan, plan.getCategory()))
                .collect(Collectors.toList());
    }
}
