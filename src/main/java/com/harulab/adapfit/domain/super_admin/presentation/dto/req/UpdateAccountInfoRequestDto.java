package com.harulab.adapfit.domain.super_admin.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateAccountInfoRequestDto {

    private final String email;
    private final String nickname;
    private final String phoneNumber;

    @Builder
    public UpdateAccountInfoRequestDto(String email, String nickname, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

}
