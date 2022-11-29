package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
public class PlanCreateRequestDto {

    private final Long categoryId;

    @NotBlank(message = TITLE_NOT_BLANK)
    private final String title;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private final String content;


    @Builder
    public PlanCreateRequestDto(Long categoryId, String title, String content) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public Plan toEntity() {
        return Plan.builder()
                .title(title)
                .content(content)
                .build();
    }
}
