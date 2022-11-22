package com.harulab.adapfit.domain.banner.service;

import com.harulab.adapfit.domain.banner.domain.Banner;
import com.harulab.adapfit.domain.banner.domain.BannerRepository;
import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public List<BannerResponse> getBannerList() {
        return bannerRepository.findAll().stream()
                .map(Banner::toResponse)
                .collect(Collectors.toList());
    }

}
