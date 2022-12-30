package com.harulab.adapfit.domain.plan.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;
import static com.harulab.adapfit.global.utils.ValidMessageConstants.IMAGE_NOT_NULL;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 19.
 */

@Getter
public class PlanUpdateRequestDto {

    private final Long categoryId;

    private final Long planId;

    @NotBlank(message = TITLE_NOT_BLANK)
    private final String title;

    private final String subTitle;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private final String content;

    @NotNull(message = IMAGE_NOT_NULL)
    private final MultipartFile thumbnail;

    private final List<Long> removal;

    @Builder
    public PlanUpdateRequestDto(
            Long planId,
            PlanUpdateInfoRequestDto req,
            MultipartFile thumbnail
    ) {
        this.categoryId = req.getCategoryId();
        this.planId = planId;
        this.title = req.getTitle();
        this.subTitle = req.getSubTitle();
        this.content = req.getContent();
        this.thumbnail = thumbnail;
        this.removal = req.getRemoval();
    }

    public boolean thumbnailIsNotNull() {
        return thumbnail != null;
    }

    public boolean removalIsNotNull() {
        return removal != null;
    }
}
