package com.harulab.adapfit.domain.plan.presentation.dto.res;

import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Getter;

@Getter
public class PlanResponseDto {

    private final Long planId;
    private final String title;
    private final String content;
    private final String imgUrl;
    private final Long writerId;

    public PlanResponseDto(Plan plan) {
        this.planId = plan.getId();
        this.title = plan.getTitle();
        this.content = plan.getContent();
        this.imgUrl = plan.getFileUrl();
        this.writerId = plan.getWriter().getId();
    }
}
