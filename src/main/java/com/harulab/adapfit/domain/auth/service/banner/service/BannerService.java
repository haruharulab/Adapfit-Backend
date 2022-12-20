package com.harulab.adapfit.domain.auth.service.banner.service;

import com.harulab.adapfit.domain.auth.service.banner.domain.Banner;
import com.harulab.adapfit.domain.auth.service.banner.facade.BannerFacade;
import com.harulab.adapfit.domain.auth.service.banner.presentation.dto.req.UploadBannerRequest;
import com.harulab.adapfit.domain.auth.service.banner.presentation.dto.res.BannerResponse;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class BannerService {

    private final BannerFacade bannerFacade;

    public List<BannerResponse> getBannerList() {
        return bannerFacade.findAll().stream()
                .map(Banner::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void upload(UploadBannerRequest dto) throws IOException {
        S3FileResponseDto fileDto = bannerFacade.saveBannerFile(dto);
        Banner banner = dto.toEntity(fileDto);
        bannerFacade.save(banner);
    }

    @Transactional
    public void delete(Long id) {
        Banner banner = bannerFacade.findById(id);
        bannerFacade.delete(banner);
    }

}
