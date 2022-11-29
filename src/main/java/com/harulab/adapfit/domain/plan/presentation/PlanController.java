package com.harulab.adapfit.domain.plan.presentation;

import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@Validated
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService planService;

    @PostMapping()
    public void createPlan(
            @RequestPart(value = "req") @Valid PlanCreateRequestDto req,
            @RequestPart(value = "image") MultipartFile image
            ) throws IOException {
        planService.createPlan(new PlanRequestDto(req, image));
    }

    @PutMapping("/{planId}")
    public void updatePlan(
            @PathVariable Long planId,
            @RequestBody @Valid PlanUpdateRequestDto req
    ) {
        planService.updatePlan(planId, req);
    }

    @DeleteMapping("/{planId}")
    public void deletePlan(
            @PathVariable Long planId
    ) {
        planService.deletePlan(planId);
    }
}
