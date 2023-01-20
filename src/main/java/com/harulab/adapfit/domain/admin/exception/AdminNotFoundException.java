package com.harulab.adapfit.domain.admin.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class AdminNotFoundException extends AdapfitException {

    public final static AdapfitException EXCEPTION = new AdminNotFoundException();

    public AdminNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
