package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

import java.util.List;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
public class PlanCreateRequestDto {

    @NotBlank(message = TITLE_NOT_BLANK)
    private final String title;

    @NotBlank(message = CONTENT_NOT_BLANK)
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
