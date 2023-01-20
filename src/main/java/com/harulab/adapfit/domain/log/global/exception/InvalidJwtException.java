package com.harulab.adapfit.domain.log.global.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class InvalidJwtException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new InvalidJwtException();

    public InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
