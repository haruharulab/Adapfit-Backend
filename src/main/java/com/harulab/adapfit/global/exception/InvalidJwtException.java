package com.harulab.adapfit.global.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class InvalidJwtException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new InvalidJwtException();

    public InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
