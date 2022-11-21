package com.harulab.adapfit.domain.user.presentation.dto.req;

import com.harulab.adapfit.domain.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    private final String phoneNumber;

    public UserRequestDto(String authId, String password, String validatePassword, String email, String phoneNumber) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        validateIsMatchedPassword();
    }

    public User toEntity() {
        return User.builder()
                .authId(authId)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }

    private void validateIsMatchedPassword() {
        if (!Objects.equals(password, validatePassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
