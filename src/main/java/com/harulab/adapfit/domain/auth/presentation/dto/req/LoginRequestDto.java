package com.harulab.adapfit.domain.auth.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "auth_Id Null, 공백, 띄워쓰기를 허용하지 않습니다.")
    private String authId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String password;

}
