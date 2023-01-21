package com.harulab.adapfit.global.security.jwt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;


@Getter
public class TokenResponseDto {

    private final String accessToken;
    private final String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final ZonedDateTime expiredAt;

    public TokenResponseDto(String accessToken, String refreshToken, ZonedDateTime expiredAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
    }
}
