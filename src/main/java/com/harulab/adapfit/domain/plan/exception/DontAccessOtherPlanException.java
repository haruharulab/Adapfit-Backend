package com.harulab.adapfit.domain.plan.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class DontAccessOtherPlanException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new DontAccessOtherPlanException();

    public DontAccessOtherPlanException() {
        super(ErrorCode.DONT_ACCESS_OTHER_PLAN);
    }
}
