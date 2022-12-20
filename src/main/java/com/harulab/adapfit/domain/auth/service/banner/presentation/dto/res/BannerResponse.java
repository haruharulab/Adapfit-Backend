package com.harulab.adapfit.domain.auth.service.banner.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BannerResponse {

    private Long id;
    private String fileUrl;
    private String link;

}
