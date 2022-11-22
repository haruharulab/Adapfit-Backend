package com.harulab.adapfit.domain.admin.presentation.dto.req;

import com.harulab.adapfit.domain.admin.domain.Admin;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateAccountInfoRequestDto {

    private final String password;
    private final String email;
    private final String nickname;
    private final String centerInfo;
    private final String phoneNumber;

    @Builder
    public UpdateAccountInfoRequestDto(String password, String email, String nickname, String centerInfo, String phoneNumber) {
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.centerInfo = centerInfo;
        this.phoneNumber = phoneNumber;
    }

    public Admin toEntity() {
        return Admin.builder()
                .password(password)
                .email(email)
                .nickname(nickname)
                .centerInfo(centerInfo)
                .phoneNumber(phoneNumber)
                .build();
    }
}
