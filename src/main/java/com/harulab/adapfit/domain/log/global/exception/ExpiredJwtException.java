package com.harulab.adapfit.domain.log.global.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class ExpiredJwtException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new ExpiredJwtException();

    public ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
