package com.harulab.adapfit.domain.plan.presentation;

import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanCreateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateInfoRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.domain.plan.service.PlanService;
import com.harulab.adapfit.global.generic.ResultResponse;
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
    public ResultResponse<List<PlanResponseDto>> getAll() {
        List<PlanResponseDto> plans = planService.getAllPlan();
        return new ResultResponse<>(plans.size(), plans);
    }

    @GetMapping("{planId}")
    public PlanResponseDto getDetail(@PathVariable Long planId) {
        return planService.getPlanDetail(planId);
    }

    @PostMapping
    public void create(
            @RequestPart(value = "req") @Valid PlanCreateRequestDto req,
            @RequestPart(value = "thumbnail") MultipartFile thumbnail,
            @RequestHeader(name = "Authorization") String token
            ) throws IOException {
        planService.createPlan(new PlanRequestDto(req, thumbnail), token);
    }

    @PutMapping("/{planId}")
    public void update(
            @PathVariable Long planId,
            @RequestPart PlanUpdateInfoRequestDto req,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestHeader(name = "Authorization") String token
    ) throws IOException {
        planService.updatePlan(new PlanUpdateRequestDto(planId, req, thumbnail), token);
    }

    @DeleteMapping("/{planId}")
    public void delete(
            @PathVariable Long planId,
            @RequestHeader(name = "Authorization") String token
    ) {
        planService.deletePlan(planId, token);
    }
}
