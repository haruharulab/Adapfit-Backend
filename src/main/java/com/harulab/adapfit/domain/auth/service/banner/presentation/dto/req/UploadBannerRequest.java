package com.harulab.adapfit.domain.auth.service.banner.presentation.dto.req;

import com.harulab.adapfit.domain.auth.service.banner.domain.Banner;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

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

}
