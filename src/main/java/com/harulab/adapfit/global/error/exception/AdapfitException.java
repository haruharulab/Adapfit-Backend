package com.harulab.adapfit.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdapfitException extends RuntimeException {

    private final ErrorCode errorCode;

}
