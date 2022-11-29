package com.harulab.adapfit.domain.apply.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class ApplyNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new ApplyNotFoundException();

    public ApplyNotFoundException() {
        super(ErrorCode.APPLY_NOT_FOUND);
    }
}
