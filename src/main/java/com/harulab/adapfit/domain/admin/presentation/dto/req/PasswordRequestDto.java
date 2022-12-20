package com.harulab.adapfit.domain.admin.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordRequestDto {

    private String newPassword;
    private String validatePassword;

    public PasswordRequestDto(String newPassword, String validatePassword) {
        this.newPassword = newPassword;
        this.validatePassword = validatePassword;
    }

}
