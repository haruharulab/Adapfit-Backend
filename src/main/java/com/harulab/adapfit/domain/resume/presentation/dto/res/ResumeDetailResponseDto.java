package com.harulab.adapfit.domain.resume.presentation.dto.res;

import com.harulab.adapfit.domain.resume.domain.Resume;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 18.
 */

@Getter
public class ResumeDetailResponseDto {

    private final Long resumeId;
    private final String email;
    private final String resume;
    private final String portFolio;
    private final String etcFile;
    private final String name;
    private final String phoneNumber;

    public ResumeDetailResponseDto(Resume resume) {
        this.resumeId = resume.getId();
        this.email = resume.getEmail();
        this.resume = resume.getResume();
        this.portFolio = resume.getPortfolio();
        this.etcFile = resume.getEtcFile();
        this.name = resume.getName();
        this.phoneNumber = resume.getPhoneNumber();
    }
}
