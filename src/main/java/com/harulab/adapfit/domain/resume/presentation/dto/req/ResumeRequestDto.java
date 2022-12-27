package com.harulab.adapfit.domain.resume.presentation.dto.req;

import com.harulab.adapfit.domain.resume.domain.Resume;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class ResumeRequestDto {

    private Long recruitmentId;
    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile resume;
    private MultipartFile portfolio;
    private MultipartFile etcFile;

    @Builder
    public ResumeRequestDto(ProxyResumeRequestDto req, MultipartFile resume, MultipartFile portfolio, MultipartFile etcFile) {
        this.recruitmentId = Long.valueOf(req.getRecruitmentId());
        this.name = req.getName();
        this.email = req.getEmail();
        this.phoneNumber = req.getPhoneNumber();
        this.resume = resume;
        this.portfolio = portfolio;
        this.etcFile = etcFile;
    }

    public Resume toEntity(Map<String, String> files) {
        return Resume.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .resume(files.get("resume"))
                .portfolio(files.get("portfolio"))
                .etcFile(files.get("etcFile"))
                .saw(false)
                .build();
    }
}
