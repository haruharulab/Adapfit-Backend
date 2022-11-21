package com.harulab.adapfit.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDto {

    private final String accessToken;
    private final String refreshToken;

}
