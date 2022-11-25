package com.harulab.adapfit.domain.plan.presentation;

import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
