package com.harulab.adapfit.domain.counseling.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

public class RoomNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new RoomNotFoundException();

    public RoomNotFoundException() {
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}
