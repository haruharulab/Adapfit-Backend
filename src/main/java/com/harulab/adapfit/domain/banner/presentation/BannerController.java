package com.harulab.adapfit.domain.banner.presentation;

import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import com.harulab.adapfit.domain.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/banner")
@RestController
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    public List<BannerResponse> getBannerList() {
        return bannerService.getBannerList();
    }

}
