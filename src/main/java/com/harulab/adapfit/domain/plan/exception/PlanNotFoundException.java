package com.harulab.adapfit.domain.plan.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class PlanNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new PlanNotFoundException();

    public PlanNotFoundException() {
        super(ErrorCode.PLAN_NOT_FOUND);
    }
}
