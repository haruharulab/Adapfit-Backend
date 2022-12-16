package com.harulab.adapfit.domain.plan.presentation;

import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public List<PlanResponseDto> getAll() {
        return planService.getAllPlan();
    }

    @GetMapping("{planId}")
    public PlanResponseDto getDetail(@PathVariable Long planId) {
        return planService.getPlanDetail(planId);
    }

    @PostMapping
    public void createPlan(
            @RequestPart(value = "req") @Valid PlanCreateRequestDto req,
            @RequestPart(value = "thumbnail") MultipartFile thumbnail,
            @RequestPart(value = "images") List<MultipartFile> images
            ) throws IOException {
        System.out.println("images = " + images);
        planService.createPlan(new PlanRequestDto(req, thumbnail, images));
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
