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
        S3FileResponseDto thumbnailRes = planFacade.saveImage(req.getThumbnail());

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
        // 만약 썸네일이 Null 이 아니라면 원래 있던 썸네일 삭제하고 새로운 썸네일으로 변경하기
        if (!req.isThumbnailNull()) {
            planFacade.deleteOriginThumbnail(req.getThumbnail());
            plan.updateThumbnail(planFacade.saveImage(req.getThumbnail()));
        }
        plan.updatePlanInfo(categoryService.detail(req.getCategoryId()), req.getTitle(), req.getContent());
        // 플랜 내용에 있는 이미지가 Null 이 아니라면 원래 있던 이미지 삭제하고 새로운 이미지들로 변경하기
        if (!req.isImagesNull()) {
            updateImages(plan, req.getImages());
        }
    }

    private void updateImages(Plan plan, List<MultipartFile> images) {
        images.forEach(image -> {
            // 클라이언트에서 요청받은 파일 명은 [pk.파일이름.확장자]이다.
            // 요청받은 pk를 가진 이미지를 삭제하고 새로운 이미지를 생성한다.
            // 만약 단순하게 이미지를 추가하는 것이라면 파일 구조는 [파일이름.확장자]로만 받는다.
            List<String> imageInfo = Arrays.asList(Objects.requireNonNull(image.getOriginalFilename()).split("\\."));
            if (imageInfo.size() == 2) {
                try {
                    addImage(plan, image);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (imageInfo.size() == 3) {
                try {
                    updateImage(imageInfo.get(0), image);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        createImages(plan, images);
    }

    private void updateImage(String imagePk, MultipartFile image) throws IOException {
        Long imageId = Long.valueOf(imagePk);
        S3FileResponseDto imageRes = imageService.getImageRes(image);
        planFacade.updateOriginImage(imageId, imageRes);
    }

    private void addImage(Plan plan, MultipartFile image) throws IOException {
        imageService.saveImage(plan, image);
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
