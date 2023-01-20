package com.harulab.adapfit.domain.category.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class CategoryNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new CategoryNotFoundException();

    public CategoryNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }
}
