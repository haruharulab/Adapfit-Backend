package com.harulab.adapfit.domain.plan.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlanUpdateInfoRequestDto {

    private Long categoryId;
    private String title;
    private String content;

    @Builder
    public PlanUpdateInfoRequestDto(Long categoryId, String title, String content) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

}
