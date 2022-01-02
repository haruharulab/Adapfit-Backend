package com.harulab.adapfit.domain.admin.presentation.dto.req;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.type.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class AdminRequestDto {

    private final String authId = UUID.randomUUID().toString().substring(0, 8);
    private String authority;

    public AdminRequestDto(String authority) {
        this.authority = authority;
    }

    public Admin toEntity() {
        return Admin.builder()
                .authId(authId)
                .email("ADMIN@ACCOUNT.COM")
                .nickname("ADMIN")
                .password("adapfit1010admin")
                .phoneNumber("phoneNumber")
                .authority(Authority.valueOf(authority))
                .build();
    }

}
