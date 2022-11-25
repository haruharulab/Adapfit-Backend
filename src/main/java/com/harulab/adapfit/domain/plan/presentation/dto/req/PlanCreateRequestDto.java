package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlanCreateRequestDto {

    private final String title;
    private final String content;

    @Builder
    public PlanCreateRequestDto(String title, String content) {
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
