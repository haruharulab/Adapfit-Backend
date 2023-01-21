package com.harulab.adapfit.domain.banner.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class BannerNotFoundException extends AdapfitException {

    public final static AdapfitException EXCEPTION = new BannerNotFoundException();

    public BannerNotFoundException() {
        super(ErrorCode.BANNER_NOT_FOUND);
    }
}
