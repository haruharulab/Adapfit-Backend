package com.harulab.adapfit.domain.banner.presentation;

import com.harulab.adapfit.domain.banner.service.BannerService;
import com.harulab.adapfit.domain.banner.presentation.dto.req.UploadBannerRequest;
import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import com.harulab.adapfit.domain.log.global.generic.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/banner")
@RestController
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    public ResultResponse<List<BannerResponse>> getBannerList() {
        List<BannerResponse> banners = bannerService.getBannerList();
        return new ResultResponse<>(banners.size(), banners);
    }

    @PostMapping
    public void upload(
            @RequestPart(value = "link", required = false) String link,
            @RequestPart(value = "image") MultipartFile image,
            @RequestHeader(name = "Authorization") String token
    ) throws IOException {
        bannerService.upload(new UploadBannerRequest(link, image), token);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable Long id,
            @RequestHeader(name = "Authorization") String token
    ) {
        bannerService.delete(id, token);
    }

}
