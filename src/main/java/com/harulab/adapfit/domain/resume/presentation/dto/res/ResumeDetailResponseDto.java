package com.harulab.adapfit.domain.resume.presentation.dto.res;

import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentResponseDto;
import com.harulab.adapfit.domain.resume.domain.Resume;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 18.
 */

@Getter
public class ResumeDetailResponseDto {

    private final Long resumeId;
    private final RecruitmentResponseDto recruitment;
    private final String email;
    private final String resume;
    private final String portFolio;
    private final String etcFile;
    private final String name;
    private final String phoneNumber;
    private final LocalDateTime createdAt;

    public ResumeDetailResponseDto(Resume resume) {
        this.resumeId = resume.getId();
        this.recruitment = new RecruitmentResponseDto(resume.getRecruitment());
        this.email = resume.getEmail();
        this.resume = resume.getResume();
        this.portFolio = resume.getPortfolio();
        this.etcFile = resume.getEtcFile();
        this.name = resume.getName();
        this.phoneNumber = resume.getPhoneNumber();
        this.createdAt = resume.getCreatedAt();
    }
}
