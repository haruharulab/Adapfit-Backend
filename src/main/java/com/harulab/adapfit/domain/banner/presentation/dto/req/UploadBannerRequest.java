package com.harulab.adapfit.domain.banner.presentation.dto.req;

import com.harulab.adapfit.domain.banner.domain.Banner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class UploadBannerRequest {

    @Length(max = 255)
    private String link;

    @Getter
    @NotNull
    private MultipartFile image;

    public Banner toEntity() {
        return Banner.builder()
                .link(link)
                .fileExt(getFileExt())
                .build();
    }

    public String getFileExt() {
        return image.getOriginalFilename().substring(
                image.getOriginalFilename().lastIndexOf(".") + 1
        );
    }

}
