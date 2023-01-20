package com.harulab.adapfit.domain.log.global.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class AdminNotFoundException extends AdapfitException {

    public static final AdminNotFoundException EXCEPTION = new AdminNotFoundException();

    public AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
