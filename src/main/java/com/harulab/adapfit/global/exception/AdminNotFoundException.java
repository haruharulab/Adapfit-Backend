package com.harulab.adapfit.global.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class AdminNotFoundException extends AdapfitException {

    public static final AdminNotFoundException EXCEPTION = new AdminNotFoundException();

    public AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
