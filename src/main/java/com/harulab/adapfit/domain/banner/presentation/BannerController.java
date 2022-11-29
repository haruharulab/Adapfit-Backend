package com.harulab.adapfit.domain.banner.presentation;

import com.harulab.adapfit.domain.banner.presentation.dto.req.UploadBannerRequest;
import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import com.harulab.adapfit.domain.banner.service.BannerService;
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
    public List<BannerResponse> getBannerList() {
        return bannerService.getBannerList();
    }

    @PostMapping
    public void upload(
            @RequestPart(value = "link") String link,
            @RequestPart(value = "image") MultipartFile image
    ) throws IOException {
        bannerService.upload(new UploadBannerRequest(link, image));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bannerService.delete(id);
    }

}
