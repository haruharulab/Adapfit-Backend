package com.harulab.adapfit.domain.user.presentation.dto.res;

import lombok.Getter;

@Getter
public class AdminCreateResponseDto {

    private final String authId;
    private final String password;

    public AdminCreateResponseDto(String authId) {
        this.authId = authId;
        this.password = "adapfit1010admin";
    }
}
