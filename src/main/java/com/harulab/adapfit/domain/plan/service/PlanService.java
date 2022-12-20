package com.harulab.adapfit.domain.plan.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.service.CategoryService;
import com.harulab.adapfit.domain.image.domain.Image;
import com.harulab.adapfit.domain.image.service.ImageService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class PlanService {

    private final PlanFacade planFacade;
    private final UserFacade userFacade;
    private final ImageService imageService;
    private final CategoryService categoryService;

    @Transactional
    public void createPlan(PlanRequestDto req) throws IOException {
        S3FileResponseDto thumbnailRes = imageService.getImageRes(req.getThumbnail());

        Plan plan = req.toEntity(thumbnailRes);
        plan.confirmWriter(userFacade.getCurrentUser());

        Category category = categoryService.savingInCategory(req.getCategoryId());
        plan.confirmCategory(category);

        createImages(plan, req.getImages());

        planFacade.save(plan);
    }

    private void createImages(Plan plan, List<MultipartFile> images) {
        images.forEach(image -> {
            try {
                imageService.saveImage(plan, image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });;
    }

    @Transactional
    public void updatePlan(PlanUpdateRequestDto req) throws IOException {
        Plan plan = planFacade.findByPlanId(req.getPlanId());
        plan.updateInfo(req, categoryService.detail(req.getCategoryId()));
        updateThumbnail(plan, req);
        isImagesNotNull(plan, req);
    }

    private void updateThumbnail(Plan plan, PlanUpdateRequestDto req) throws IOException {
        if (!req.isThumbnailNull()) 
            plan.updateThumbnail
                    (imageService.getImageRes(req.getThumbnail()));
    }

    private void isImagesNotNull(Plan plan, PlanUpdateRequestDto req) throws IOException {
        if (!req.isImagesNull()) updateImages(plan, req.getImages());
    }

    private void updateImages(Plan plan, List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) {
            List<String> imageSculpture = Arrays.asList(Objects.requireNonNull(image.getOriginalFilename()).split("\\."));
            if (imageSculpture.size() == 2) {
                addImage(plan, image);
            }
            if (imageSculpture.size() == 3) {
                updateImage(Long.valueOf(imageSculpture.get(0)), image);
            }
        }
    }

    private void addImage(Plan plan, MultipartFile image) throws IOException {
        S3FileResponseDto imageRes = imageService.getImageRes(image);
        imageService.createImage(plan, imageRes);
    }

    private void updateImage(Long imageId, MultipartFile image) throws IOException {
        imageService.deleteOriginFile(imageId);
        imageService.updateImage(imageId, image);
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
