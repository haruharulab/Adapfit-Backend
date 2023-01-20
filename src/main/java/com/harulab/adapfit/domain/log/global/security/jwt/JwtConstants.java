package com.harulab.adapfit.domain.log.global.security.jwt;

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
    USER_ROLE("USER"),
    ADMIN_ROLE("ADMIN"),
    SUPER_ADMIN_ROLE("SUPER_ADMIN");

    private final String message;
}
