package com.harulab.adapfit.domain.plan.presentation.dto.res;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlanResponseDto {

    private Long planId;
    private String title;
    private String subTitle;
    private String content;
    private String thumbnail;
    private CategoryResponseDto category;

    public PlanResponseDto(Plan plan, Category category) {
        this.planId = plan.getId();
        this.title = plan.getTitle();
        this.subTitle = plan.getSubTitle();
        this.content = plan.getContent();
        this.thumbnail = plan.getThumbnailUrl();
        this.category = new CategoryResponseDto(category);
    }
}
