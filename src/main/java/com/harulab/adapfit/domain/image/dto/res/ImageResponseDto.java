package com.harulab.adapfit.domain.image.dto.res;

import com.harulab.adapfit.domain.image.domain.Image;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 16.
 */

@Getter
public class ImageResponseDto {

    private final String imageName;
    private final String imageUrl;

    public ImageResponseDto(Image image) {
        this.imageName = image.getImageName();
        this.imageUrl = image.getImageUrl();
    }
}
