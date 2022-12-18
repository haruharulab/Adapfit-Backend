package com.harulab.adapfit.domain.resume.presentation.dto.res;

import com.harulab.adapfit.domain.resume.domain.Resume;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 18.
 */

@Getter
public class ResumeResponseDto {
    private final Long resumeId;
    private final Long recruitmentId;
    private final String position;

    public ResumeResponseDto(Resume resume) {
        this.resumeId = resume.getId();
        this.recruitmentId = resume.getRecruitment().getId();
        this.position = resume.getRecruitment().getPosition().name();
    }
}
