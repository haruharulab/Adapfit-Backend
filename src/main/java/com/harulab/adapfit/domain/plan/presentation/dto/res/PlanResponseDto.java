package com.harulab.adapfit.domain.plan.presentation.dto.res;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Getter;

@Getter
public class PlanResponseDto {

    private final Long planId;
    private final String title;
    private final String content;
    private final String imgUrl;
    private final Long writerId;
    private final CategoryResponseDto category;

    public PlanResponseDto(Plan plan, Category category) {
        this.planId = plan.getId();
        this.title = plan.getTitle();
        this.content = plan.getContent();
        this.imgUrl = plan.getFileUrl();
        this.writerId = plan.getWriter().getId();
        this.category = new CategoryResponseDto(category);
    }
}
