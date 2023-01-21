package com.harulab.adapfit.global.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class ExpiredJwtException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new ExpiredJwtException();

    public ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
