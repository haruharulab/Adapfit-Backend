package com.harulab.adapfit.domain.apply.presentation.dto.req;

import com.harulab.adapfit.domain.apply.domain.Apply;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class ApplySubmitRequestDto {

    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile file;

    public ApplySubmitRequestDto(ProxyApplyRequestDto req, MultipartFile file) {
        this.name = req.getName();
        this.email = req.getEmail();
        this.phoneNumber = req.getPhoneNumber();
        this.file = file;
    }

    public Apply toEntity(S3FileResponseDto res) {
        return Apply.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .fileName(res.getFileName())
                .fileUrl(res.getFileUrl())
                .build();
    }
}
