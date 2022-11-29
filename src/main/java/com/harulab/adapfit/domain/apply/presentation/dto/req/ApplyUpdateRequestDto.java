package com.harulab.adapfit.domain.apply.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class ApplyUpdateRequestDto {

    private Long applyId;
    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile file;

    public ApplyUpdateRequestDto(ProxyApplyUpdateRequestDto req, MultipartFile file) {
        this.applyId = req.getApplyId();
        this.name = req.getName();
        this.email = req.getEmail();
        this.phoneNumber = req.getPhoneNumber();
        this.file = file;
    }

}