package com.harulab.adapfit.domain.super_admin.presentation.dto.req;

import com.harulab.adapfit.domain.super_admin.domain.SuperAdmin;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import com.harulab.adapfit.domain.user.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
public class    SuperAdminCreateRequestDto {

    private final String authId;
    private final String password;
    private final String validatePassword;
    private final String email;
    @Size(min = 3, max = 8)
    private final String nickname;

    @Builder
    public SuperAdminCreateRequestDto(String authId, String password, String validatePassword, String email, String nickname) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.nickname = nickname;
        validateIsMatchedPassword();
    }

    public SuperAdmin toEntity() {
        return SuperAdmin.builder()
                .authId(authId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .authority(Authority.SUPER_ADMIN)
                .build();
    }

    private void validateIsMatchedPassword() {
        if (!Objects.equals(password, validatePassword)) {
            throw PasswordNotMatchException.EXCEPTION;
        }
    }
}
