package com.harulab.adapfit.domain.auth.service.banner.facade;

import com.harulab.adapfit.domain.auth.service.banner.domain.repository.BannerRepository;
import com.harulab.adapfit.domain.auth.service.banner.exception.BannerNotFoundException;
import com.harulab.adapfit.domain.auth.service.banner.domain.Banner;
import com.harulab.adapfit.domain.auth.service.banner.presentation.dto.req.UploadBannerRequest;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BannerFacade {

    private final BannerRepository bannerRepository;
    private final S3Uploader s3Uploader;

    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public Banner findById(Long id) {
        return bannerRepository.findById(id)
                .orElseThrow(BannerNotFoundException::new);
    }

    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    public S3FileResponseDto saveBannerFile(UploadBannerRequest dto) throws IOException {
        return s3Uploader.saveFile(dto.getImage());
    }

    public void delete(Banner banner) {
        bannerRepository.delete(banner);
        s3Uploader.deleteFile(banner.getFileName());
    }

}
