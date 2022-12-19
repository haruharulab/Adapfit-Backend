package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;
import static com.harulab.adapfit.global.utils.ValidMessageConstants.CONTENT_NOT_BLANK;

@Getter
public class PlanRequestDto {

    private final Long categoryId;

    @NotBlank(message = TITLE_NOT_BLANK)
    private final String title;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private final String content;

    @Getter
    @NotNull(message = IMAGE_NOT_NULL)
    private final List<MultipartFile> images;

    @Getter
    @NotNull(message = IMAGE_NOT_NULL)
    private final MultipartFile thumbnail;

    public PlanRequestDto(PlanCreateRequestDto req, MultipartFile thumbnail, List<MultipartFile> images) {
        this.categoryId = req.getCategoryId();
        this.title = req.getTitle();
        this.content = req.getContent();
        this.images = images;
        this.thumbnail = thumbnail;
    }

    public Plan toEntity(S3FileResponseDto fileDto) {
        return Plan.builder()
                .title(title)
                .content(content)
                .thumbnailName(fileDto.getFileName())
                .thumbnailUrl(fileDto.getFileUrl())
                .build();
    }

}
