package com.harulab.adapfit.domain.user.presentation.dto.req;

import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserRequestDto {

    private final String authId = UUID.randomUUID().toString().substring(0, 8);

    public User toEntity() {
        return User.builder()
                .authId(authId)
                .email("ADMIN@ACCOUNT.COM")
                .nickname("ADMIN")
                .password("adapfit1010admin")
                .phoneNumber("phoneNumber")
                .authority(Authority.ADMIN)
                .build();
    }

}
