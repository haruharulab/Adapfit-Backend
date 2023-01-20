package com.harulab.adapfit.domain.log.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdapfitException extends RuntimeException {

    private final ErrorCode errorCode;

}
