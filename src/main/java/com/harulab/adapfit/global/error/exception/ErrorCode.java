package com.harulab.adapfit.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    PASSWORD_NOT_MATCH(400, "AUTH-400-1", "Password Not Match"),
    SAVE_IMAGE_FAILED(400, "COMMON-400-1", "Save Image Failed"),
    CANNOT_CHEER_MYSELF(400, "SOCKET-400-1", "Can Not Cheer Myself"),
    IMAGE_VALUE_NOT_FOUND(400, "COMMON-404-1", "Image Value Not Found"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired Jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid Jwt"),
    PASSWORD_MISMATCH(401, "AUTH-401-1", "Password Mismatch"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    ADMIN_NOT_FOUND(404, "ADMIN-404-1", "Admin Not Found"),
    CREDENTIALS_NOT_FOUND(404, "USER-404-3", "Credentials Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    AUTH_ID_NOT_FOUND(404, "AUTH-404-4", "AuthId(Username) Not Found"),
    PHONE_NUMBER_NOT_FOUND(404, "USER-404-5", "PhoneNumber Not Found"),



    ALREADY_CREATED(409, "SECTION-409-1", "Already Created"),
    USER_EXISTS(409, "USER-409-1", "User Exists"),
    ALREADY_JOINED(409, "USER-409-2", "Already Joined"),

    REDIS_TRANSACTION_EXCEPTION(500, "REDIS-500-1", "Cannot Read Cache From Redis"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;

}
