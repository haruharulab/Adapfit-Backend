package com.harulab.adapfit.domain.notification.exception;

import com.harulab.adapfit.global.error.exception.AdapfitException;
import com.harulab.adapfit.global.error.exception.ErrorCode;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */
public class NotificationNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new NotificationNotFoundException();

    public NotificationNotFoundException() {
        super(ErrorCode.NOTIFICATION_NOT_FOUND);
    }
}
