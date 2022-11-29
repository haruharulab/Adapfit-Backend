package com.harulab.adapfit.domain.apply.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProxyApplyRequestDto {

    private String name;

    private String email;

    private String phoneNumber;

    @Builder
    public ProxyApplyRequestDto(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
