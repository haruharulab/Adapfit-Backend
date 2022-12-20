package com.harulab.adapfit.global.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 20.
 */

@Getter
public class TokenRefreshResponseDto {

    private final String accessToken;

    @Builder
    public TokenRefreshResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
