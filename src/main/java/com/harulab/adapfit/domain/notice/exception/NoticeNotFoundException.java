package com.harulab.adapfit.domain.notice.exception;

import com.harulab.adapfit.domain.log.global.error.exception.AdapfitException;
import com.harulab.adapfit.domain.log.global.error.exception.ErrorCode;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */
public class NoticeNotFoundException extends AdapfitException {

    public static final AdapfitException EXCEPTION = new NoticeNotFoundException();

    public NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
