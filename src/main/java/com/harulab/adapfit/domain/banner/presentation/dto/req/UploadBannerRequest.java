package com.harulab.adapfit.domain.banner.presentation.dto.req;

import com.harulab.adapfit.domain.banner.domain.Banner;
import com.harulab.adapfit.global.s3.S3FileResponseDto;
import com.harulab.adapfit.global.utils.ValidMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import java.util.Objects;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@AllArgsConstructor
public class UploadBannerRequest {

    @Length(max = 255, message = LINK_SIZE)
    private String link;

    @Getter
    @NotNull(message = IMAGE_NOT_NULL)
    private MultipartFile image;

    public Banner toEntity(S3FileResponseDto fileDto) {
        return Banner.builder()
                .link(link)
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
