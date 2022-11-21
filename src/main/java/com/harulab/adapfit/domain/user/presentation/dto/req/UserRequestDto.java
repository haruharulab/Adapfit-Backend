package com.harulab.adapfit.domain.user.presentation.dto.req;

import com.harulab.adapfit.domain.user.domain.User;

public class UserRequestDto {

    private final String authId;
    private final String password;
    private final String validatePassword;
    private final String email;
    private final Integer phoneNumber;

    public UserRequestDto(String authId, String password, String validatePassword, String email, Integer phoneNumber) {
        this.authId = authId;
        this.password = password;
        this.validatePassword = validatePassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User toEntity() {
        return User.builder()
                .authId(authId)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
