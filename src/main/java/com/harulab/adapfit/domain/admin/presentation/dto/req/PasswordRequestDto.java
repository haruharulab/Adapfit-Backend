package com.harulab.adapfit.domain.admin.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
@NoArgsConstructor
public class PasswordRequestDto {

    @NotBlank(message = PASSWORD_ID_NOT_BLANK)
    private String newPassword;

    @NotBlank(message = VALIDATE_PASSWORD_ID_NOT_BLANK)
    private String validatePassword;

    public PasswordRequestDto(String newPassword, String validatePassword) {
        this.newPassword = newPassword;
        this.validatePassword = validatePassword;
    }

}
