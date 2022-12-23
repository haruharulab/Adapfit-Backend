package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.global.utils.ValidMessageConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
@NoArgsConstructor
public class PlanCreateRequestDto {

    private Long categoryId;

    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    private String subTitle;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private String content;


    @Builder
    public PlanCreateRequestDto(Long categoryId, String title, String subTitle, String content) {
        this.categoryId = categoryId;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
    }

    public Plan toEntity() {
        return Plan.builder()
                .title(title)
                .content(content)
                .build();
    }
}
