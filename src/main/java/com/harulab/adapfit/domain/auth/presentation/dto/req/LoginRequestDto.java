package com.harulab.adapfit.domain.auth.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.harulab.adapfit.domain.log.global.utils.ValidMessageConstants.AUTH_ID_NOT_BLANK;
import static com.harulab.adapfit.domain.log.global.utils.ValidMessageConstants.PASSWORD_ID_NOT_BLANK;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = AUTH_ID_NOT_BLANK)
    private String authId;

    @NotBlank(message = PASSWORD_ID_NOT_BLANK)
    private String password;

}
