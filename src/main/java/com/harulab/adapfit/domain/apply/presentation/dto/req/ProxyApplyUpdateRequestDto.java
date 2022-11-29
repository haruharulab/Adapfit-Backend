package com.harulab.adapfit.domain.apply.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProxyApplyUpdateRequestDto {

    private Long applyId;
    private String name;

    private String email;

    private String phoneNumber;

    @Builder
    public ProxyApplyUpdateRequestDto(Long applyId, String name, String email, String phoneNumber) {
        this.applyId = applyId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
