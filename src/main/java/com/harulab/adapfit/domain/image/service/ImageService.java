package com.harulab.adapfit.domain.image.service;

import com.harulab.adapfit.domain.image.domain.Image;
import com.harulab.adapfit.domain.image.facade.ImageFacade;
import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 16.
 */

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ImageService {

    private final ImageFacade imageFacade;
    private final S3Uploader s3Uploader;

    @Transactional
    public void saveImage(Plan plan, MultipartFile multipartFile) throws IOException {
        S3FileResponseDto res = s3Uploader.saveFile(multipartFile);
        imageFacade.create(new Image(res.getFileName(), res.getFileUrl())).confirmPlan(plan);
    }

    public Image getDetail(Long imageId) {
        return imageFacade.detail(imageId);
    }
}
