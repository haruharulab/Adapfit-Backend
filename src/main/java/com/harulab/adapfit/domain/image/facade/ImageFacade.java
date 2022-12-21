package com.harulab.adapfit.domain.image.facade;

import com.harulab.adapfit.domain.image.domain.Image;
import com.harulab.adapfit.domain.image.domain.repository.ImageRepository;
import com.harulab.adapfit.domain.image.exception.ImageNotFoundException;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 16.
 */

@Component
@RequiredArgsConstructor
public class ImageFacade {

    private final ImageRepository imageRepository;

    public Image create(Image image) {
        return imageRepository.save(image);
    }

    public Image detail(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> ImageNotFoundException.EXCEPTION);
    }

    public Image save(S3FileResponseDto imageRes) {
        return imageRepository.save(
                Image.builder()
                        .fileName(imageRes.getFileName())
                        .fileUrl(imageRes.getFileUrl())
                        .build()
        );
    }

    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
