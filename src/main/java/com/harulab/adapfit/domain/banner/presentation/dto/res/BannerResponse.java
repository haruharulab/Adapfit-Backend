package com.harulab.adapfit.domain.banner.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BannerResponse {

    private Long id;
    private String fileExt;
    private String link;

}
