package com.harulab.adapfit.global.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 26.
 */
public class RootNotFoundException extends AdapfitException {

    public final static AdapfitException EXCEPTION = new RootNotFoundException();

    public RootNotFoundException() {
        super(ErrorCode.ROOT_NOT_FOUND);
    }
}
