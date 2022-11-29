package com.harulab.adapfit.domain.resume.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class ResumeNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new ResumeNotFoundException();

    public ResumeNotFoundException() {
        super(ErrorCode.RESUME_NOT_FOUND);
    }
}
