package com.harulab.adapfit.domain.category.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class AlreadyExistsCategory extends AdapfitException {

    public static final AdapfitException EXCEPTION = new AlreadyExistsCategory();

    public AlreadyExistsCategory() {
        super(ErrorCode.ALREADY_EXISTS_CATEGORY);
    }
}
