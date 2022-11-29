package com.harulab.adapfit.domain.resume.presentation.dto.req;

import com.harulab.adapfit.domain.resume.domain.Resume;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class ResumeRequestDto {

    private Long recruitmentId;
    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile file;

    public ResumeRequestDto(ProxyResumeRequestDto req, MultipartFile file) {
        this.recruitmentId = Long.valueOf(req.getRecruitmentId());
        this.name = req.getName();
        this.email = req.getEmail();
        this.phoneNumber = req.getPhoneNumber();
        this.file = file;
    }

    public Resume toEntity(S3FileResponseDto res) {
        return Resume.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .fileName(res.getFileName())
                .fileUrl(res.getFileUrl())
                .build();
    }
}
