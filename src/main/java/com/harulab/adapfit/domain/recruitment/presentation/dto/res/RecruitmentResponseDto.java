package com.harulab.adapfit.domain.recruitment.presentation.dto.res;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import lombok.Getter;

@Getter
public class RecruitmentResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String position;
    private final String career;
    private final String employmentPattern;
    private final String workingArea;

    public RecruitmentResponseDto(Recruitment recruitment) {
        this.id = recruitment.getId();
        this.title = recruitment.getTitle();
        this.content = recruitment.getContent();
        this.position = recruitment.getPosition().name();
        this.career = recruitment.getCareer();
        this.employmentPattern = recruitment.getEmploymentPattern().name();
        this.workingArea = recruitment.getWorkingArea();
    }
}
