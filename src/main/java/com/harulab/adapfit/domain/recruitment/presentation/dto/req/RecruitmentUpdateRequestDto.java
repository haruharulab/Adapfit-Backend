package com.harulab.adapfit.domain.recruitment.presentation.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecruitmentUpdateRequestDto {

    private String title;
    private String content;
    private String group;
    private Integer career;
    private String employmentPattern;
    private String workingArea;

    @Builder
    public RecruitmentUpdateRequestDto(String title, String content, String group, Integer career, String employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.group = group;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }
}
