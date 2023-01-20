package com.harulab.adapfit.domain.banner.presentation.dto.req;

import com.harulab.adapfit.domain.banner.domain.Banner;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import static com.harulab.adapfit.domain.log.global.utils.ValidMessageConstants.IMAGE_NOT_NULL;
import static com.harulab.adapfit.domain.log.global.utils.ValidMessageConstants.LINK_SIZE;

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
