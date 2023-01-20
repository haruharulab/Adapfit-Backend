package com.harulab.adapfit.domain.position.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */
public class PositionNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new PositionNotFoundException();

    public PositionNotFoundException() {
        super(ErrorCode.POSITION_NOT_FOUND);
    }
}
