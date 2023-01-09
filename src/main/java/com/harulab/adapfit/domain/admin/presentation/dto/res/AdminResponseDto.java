package com.harulab.adapfit.domain.admin.presentation.dto.res;

import com.harulab.adapfit.domain.admin.domain.Admin;
import lombok.Getter;

@Getter
public class AdminResponseDto {

    private final Long userId;
    private final String authId;
    private final String email;
    private final String phoneNumber;
    private final String nickname;
    private final String authority;
    private final String center;

    public AdminResponseDto(Admin admin) {
        this.userId = admin.getId();
        this.authId = admin.getAuthId();
        this.email = admin.getEmail();
        this.phoneNumber = admin.getPhoneNumber();
        this.nickname = admin.getNickname();
        this.authority = admin.getAuthority().name();
        this.center = admin.getCenter();
    }
}
