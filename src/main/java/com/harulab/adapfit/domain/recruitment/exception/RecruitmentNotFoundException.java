package com.harulab.adapfit.domain.recruitment.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

public class RecruitmentNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new RecruitmentNotFoundException();

    public RecruitmentNotFoundException() {
        super(ErrorCode.RECRUITMENT_NOT_FOUND);
    }
}
