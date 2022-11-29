package com.harulab.adapfit.domain.plan.facade;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.domain.repository.PlanRepository;
import com.harulab.adapfit.domain.plan.exception.PlanNotFoundException;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class PlanFacade {
    private final PlanRepository planRepository;
    private final S3Uploader s3Uploader;

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public S3FileResponseDto savePlanFile(PlanRequestDto dto) throws IOException {
        return s3Uploader.saveFile(dto.getImage());
    }

    public Plan findByPlanId(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> PlanNotFoundException.EXCEPTION);
    }

    public void deletePlan(Plan plan) {
        planRepository.delete(plan);
    }
}
