package com.harulab.adapfit.domain.plan.presentation.dto.req;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.global.infrastructure.s3.S3FileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;
import static com.harulab.adapfit.global.utils.ValidMessageConstants.CONTENT_NOT_BLANK;

@Getter
@AllArgsConstructor
public class PlanRequestDto {

    private final Long categoryId;

    @NotBlank(message = TITLE_NOT_BLANK)
    private final String title;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private final String content;

    @Getter
    @NotNull(message = IMAGE_NOT_NULL)
    private MultipartFile image;

    @Builder
    public PlanRequestDto(PlanCreateRequestDto req, MultipartFile image) {
        this.categoryId = req.getCategoryId();
        this.title = req.getTitle();
        this.content = req.getContent();
        this.image = image;
    }

    public Plan toEntity(S3FileResponseDto fileDto) {
        return Plan.builder()
                .title(title)
                .content(content)
                .fileName(fileDto.getFileName())
                .fileUrl(fileDto.getFileUrl())
                .build();
    }

    public String getFileExt() {
        return Objects.requireNonNull(image.getOriginalFilename()).substring(
                image.getOriginalFilename().lastIndexOf(".") + 1
        );
    }

}
