package com.harulab.adapfit.domain.recruitment.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecruitmentUpdateRequestDto {

    private String title;
    private String content;
    private String position;
    private Integer career;
    private String employmentPattern;
    private String workingArea;

    @Builder
    public RecruitmentUpdateRequestDto(String title, String content, String position, Integer career, String employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.position = position;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }
}
