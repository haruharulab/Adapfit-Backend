package com.harulab.adapfit.domain.plan.presentation;

import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public void createPlan(@RequestBody @Valid PlanCreateRequestDto req) {
        planService.createPlan(req);
    }

    @PutMapping("/{planId}")
    public void updatePlan(
            @PathVariable Long planId,
            @RequestBody @Valid PlanUpdateRequestDto req
    ) {
        planService.updatePlan(planId, req);
    }
}
