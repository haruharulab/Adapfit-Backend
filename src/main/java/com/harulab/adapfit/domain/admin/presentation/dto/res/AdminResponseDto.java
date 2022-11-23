package com.harulab.adapfit.domain.admin.presentation.dto.res;

import com.harulab.adapfit.domain.admin.domain.Admin;
import lombok.Getter;

@Getter
public class AdminResponseDto {

    private final Long id;
    private final String authId;
    private final String password;
    private final String email;
    private final String nickname;
    private final String centerInfo;
    private final String phoneNumber;

    public AdminResponseDto(Admin admin) {
        this.id = admin.getId();
        this.authId = admin.getAuthId();
        this.password = admin.getPassword();
        this.email = admin.getEmail();
        this.nickname = admin.getNickname();
        this.centerInfo = admin.getCenterInfo();
        this.phoneNumber = admin.getPhoneNumber();
    }
}
