package com.harulab.adapfit.domain.root.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class PasswordNotMatchException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new PasswordNotMatchException();

    public PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}
