package com.harulab.adapfit.domain.log.global.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class AuthIdNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new AuthIdNotFoundException();

    public AuthIdNotFoundException() {
        super(ErrorCode.AUTH_ID_NOT_FOUND);
    }
}
