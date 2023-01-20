package com.harulab.adapfit.domain.log.global.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class NotFoundAuthIdException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new NotFoundAuthIdException();

    public NotFoundAuthIdException() {
        super(ErrorCode.AUTH_ID_NOT_FOUND);
    }
}
