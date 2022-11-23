package com.harulab.adapfit.domain.admin.presentation.dto.req;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.type.JoinStatus;
import com.harulab.adapfit.domain.admin.exception.PasswordNotMatchException;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class JoinAdminRequestDto {

    private final String authId;
    private final String password;
    private final String validatePassword;
    private final String email;
    private final String nickname;
    private final String centerInfo;
    private final String phoneNumber;

    @Builder
    public JoinAdminRequestDto(String authId, String password, String validatePassword, String email, String nickname, String centerInfo, String phoneNumber) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.nickname = nickname;
        this.centerInfo = centerInfo;
        this.phoneNumber = phoneNumber;
        validateIsMatchedPassword();
    }

    public Admin toEntity() {
        return Admin.builder()
                .authId(authId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .centerInfo(centerInfo)
                .phoneNumber(phoneNumber)
                .authority(Authority.USER)
                .joinStatus(JoinStatus.WAITING)
                .build();
    }

    private void validateIsMatchedPassword() {
        if (!Objects.equals(password, validatePassword)) {
            throw PasswordNotMatchException.EXCEPTION;
        }
    }
}
