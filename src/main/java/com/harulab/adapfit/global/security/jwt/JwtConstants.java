package com.harulab.adapfit.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JwtConstants {

    ACCESS_KEY("access_token"),
    REFRESH_KEY("refresh_token"),
    EMPTY(" "),
    TYPE("type"),
    ROLE("role"),
    AUTH_ID("authId"),
    USER_ROLE("USER");

    private final String message;
}
