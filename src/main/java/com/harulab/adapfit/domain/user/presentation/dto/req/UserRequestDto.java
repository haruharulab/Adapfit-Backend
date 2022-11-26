package com.harulab.adapfit.domain.user.presentation.dto.req;

import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import com.harulab.adapfit.domain.user.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
public class UserRequestDto {

    @NotNull
    private final String authId;

    @NotNull
    private final String password;

    @NotNull
    private final String validatePassword;

    @NotNull
    @Email
    private final String email;

    @NotNull
    @Size(min = 2, max = 8)
    private final String nickname;

    @NotNull
    private final String phoneNumber;

    @Builder
    public UserRequestDto(String authId, String password, String validatePassword, String email, String nickname, String phoneNumber) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        validateIsMatchedPassword();
    }

    public User toEntity() {
        return User.builder()
                .authId(authId)
                .email(email)
                .nickname(nickname)
                .password(password)
                .phoneNumber(phoneNumber)
                .authority(Authority.USER)
                .build();
    }

    private void validateIsMatchedPassword() {
        if (!Objects.equals(password, validatePassword)) {
            throw PasswordNotMatchException.EXCEPTION;
        }
    }
}
