package com.harulab.adapfit.domain.auth.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = AUTH_ID_NOT_BLANK)
    private String authId;

    @NotBlank(message = PASSWORD_ID_NOT_BLANK)
    private String password;

}
