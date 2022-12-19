package com.harulab.adapfit.domain.image.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 19.
 */
public class ImageNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new ImageNotFoundException();

    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
