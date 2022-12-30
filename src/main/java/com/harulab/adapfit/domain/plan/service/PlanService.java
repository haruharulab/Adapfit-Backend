package com.harulab.adapfit.domain.plan.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.service.CategoryService;
import com.harulab.adapfit.domain.image.service.ImageService;
import com.harulab.adapfit.domain.log.service.LogService;
import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.facade.PlanFacade;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.domain.admin.facade.AdminFacade;
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
    private final AdminFacade adminFacade;
    private final ImageService imageService;
    private final CategoryService categoryService;
    private final LogService logService;

    @Transactional
    public void createPlan(PlanRequestDto req, String token) throws IOException {
        S3FileResponseDto thumbnailRes = imageService.getImageRes(req.getThumbnail());
        Plan plan = req.toEntity(thumbnailRes);

        Category category = categoryService.savingInCategory(req.getCategoryId());
        plan.confirmCategory(category);
        planFacade.save(plan);
        logService.save(req.getTitle() + " 플랜을 생성하였습니다.", token);
    }

    @Transactional
    public void updatePlan(PlanUpdateRequestDto req, String token) throws IOException {
        Plan plan = planFacade.findByPlanId(req.getPlanId());
        plan.updateInfo(req, categoryService.detail(req.getCategoryId()));

        updateThumbnail(plan, req);
        isRemovalNotNull(req);
        logService.save(req.getTitle() + " 플랜을 수정하였습니다.", token);
    }

    private void isRemovalNotNull(PlanUpdateRequestDto req) {
        if (req.removalIsNotNull()) {
            for (Long image : req.getRemoval()) {
                imageService.deleteOriginFile(image);
            }
        }
    }

    private void updateThumbnail(Plan plan, PlanUpdateRequestDto req) throws IOException {
        if (req.thumbnailIsNotNull())
            plan.updateThumbnail
                    (imageService.getImageRes(req.getThumbnail()));
    }

    @Transactional
    public void deletePlan(Long planId, String token) {
        Plan plan = planFacade.findByPlanId(planId);
        Admin admin = adminFacade.getCurrentAdmin();
        logService.save(plan.getTitle() + " 플랜을 삭제하였습니다.", token);

        plan.isRightWriter(admin);
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
