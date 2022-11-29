package com.harulab.adapfit.domain.root.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@Getter
public class UpdateAccountInfoRequestDto {

    private final String email;

    @Size(message = NICKNAME_SIZE)
    private final String nickname;
    private final String phoneNumber;

    @Builder
    public UpdateAccountInfoRequestDto(String email, String nickname, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

}
