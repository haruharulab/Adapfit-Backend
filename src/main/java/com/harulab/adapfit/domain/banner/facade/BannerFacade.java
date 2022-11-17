package com.harulab.adapfit.domain.banner.facade;

import com.harulab.adapfit.domain.banner.domain.Banner;
import com.harulab.adapfit.domain.banner.domain.repository.BannerRepository;
import com.harulab.adapfit.domain.banner.presentation.dto.req.UploadBannerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BannerFacade {

    private final BannerRepository bannerRepository;

    @Value("${file.path.base}")
    private String PUBLIC_RESOURCE_PATH;
    @Value("${file.path.upload.banner}")
    private String BANNER_UPLOAD_PATH;

    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public void saveBannerFile(UploadBannerRequest dto, Banner banner) throws IOException {
        File dir = new File(PUBLIC_RESOURCE_PATH + BANNER_UPLOAD_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir.getPath() + "/" + banner.getId() + "." + banner.getFileExt());
        dto.getImage().transferTo(file);
    }

    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

}
