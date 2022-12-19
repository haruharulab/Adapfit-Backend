package com.harulab.adapfit.domain.plan.presentation.dto.res;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.image.dto.res.ImageResponseDto;
import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlanResponseDto {

    private final Long planId;
    private final String title;
    private final String content;
    private final String thumbnail;
    private final Long writerId;
    private final CategoryResponseDto category;
    private final List<ImageResponseDto> images;

    public PlanResponseDto(Plan plan, Category category) {
        this.planId = plan.getId();
        this.title = plan.getTitle();
        this.content = plan.getContent();
        this.thumbnail = plan.getThumbnailUrl();
        this.writerId = plan.getWriter().getId();
        this.category = new CategoryResponseDto(category);
        this.images = plan.getImages().stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }
}
