package com.harulab.adapfit.domain.resume.presentation.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class ResumeUpdateRequestDto {

    private Long applyId;
    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile file;

    public ResumeUpdateRequestDto(ProxyResumeUpdateRequestDto req, MultipartFile file) {
        this.applyId = Long.valueOf(req.getResumeId());
        this.name = req.getName();
        this.email = req.getEmail();
        this.phoneNumber = req.getPhoneNumber();
        this.file = file;
    }

}