package com.harulab.adapfit.domain.resume.presentation.dto.res;

import com.harulab.adapfit.domain.resume.domain.Resume;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 18.
 */

@Getter
public class ResumeResponseDto {
    private final Long resumeId;
    private final Long recruitmentId;
    private final String name;
    private final LocalDateTime createdAt;
    private final boolean saw;

    public ResumeResponseDto(Resume resume) {
        this.resumeId = resume.getId();
        this.recruitmentId = resume.getRecruitment().getId();
        this.name = resume.getName();
        this.createdAt = resume.getCreatedAt();
        this.saw = resume.isSaw();
    }
}
