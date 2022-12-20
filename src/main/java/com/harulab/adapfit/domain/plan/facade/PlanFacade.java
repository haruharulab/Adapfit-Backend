package com.harulab.adapfit.domain.plan.facade;

import com.harulab.adapfit.domain.image.domain.Image;
import com.harulab.adapfit.domain.image.domain.repository.ImageRepository;
import com.harulab.adapfit.domain.image.exception.ImageNotFoundException;
import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.domain.repository.PlanRepository;
import com.harulab.adapfit.domain.plan.exception.PlanNotFoundException;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PlanFacade {
    private final PlanRepository planRepository;
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public S3FileResponseDto saveImage(MultipartFile image) throws IOException {
        return s3Uploader.saveFile(image);
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

    public void deleteOriginThumbnail(MultipartFile thumbnail) {
        s3Uploader.deleteFile(thumbnail.getOriginalFilename());
    }

    public void updateOriginImage(Long imagePk, S3FileResponseDto fileRes) {
        Image image = imageRepository.findById(imagePk)
                .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

        image.updateImageInfo(fileRes.getFileName(), fileRes.getFileUrl());
    }

}
