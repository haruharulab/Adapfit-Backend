package com.harulab.adapfit.domain.user.presentation.dto.res;

import com.harulab.adapfit.domain.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long user_pk;
    private final String email;
    private final String phoneNumber;
    private final String authority;

    public UserResponseDto(User user) {
        this.user_pk = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.authority = user.getAuthority().name();
    }
}
