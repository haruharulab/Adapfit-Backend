package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.global.utils.ValidMessageConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@NoArgsConstructor
@Getter
public class PlanUpdateInfoRequestDto {

    @NotNull(message = CATEGORY_ID_NOT_BLANK)
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
