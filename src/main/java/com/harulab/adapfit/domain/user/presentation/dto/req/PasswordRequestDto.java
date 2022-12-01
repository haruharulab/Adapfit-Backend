package com.harulab.adapfit.domain.user.presentation.dto.req;

import lombok.Getter;

@Getter
public class PasswordRequestDto {

    private final String newPassword;
    private final String validatePassword;

    public PasswordRequestDto(String newPassword, String validatePassword) {
        this.newPassword = newPassword;
        this.validatePassword = validatePassword;
    }

}
