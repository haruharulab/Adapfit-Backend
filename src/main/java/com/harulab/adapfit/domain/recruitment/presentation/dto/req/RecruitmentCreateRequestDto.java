package com.harulab.adapfit.domain.recruitment.presentation.dto.req;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import com.harulab.adapfit.domain.recruitment.domain.type.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecruitmentCreateRequestDto {

    private String title;
    private String content;
    private String position;
    private Integer career;
    private String employmentPattern;
    private String workingArea;

    @Builder
    public RecruitmentCreateRequestDto(String title, String content, String position, Integer career, String employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.position = position;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }

    public Recruitment toEntity() {
        return Recruitment.builder()
                .title(title)
                .content(content)
                .position(Position.valueOf(position))
                .career(career)
                .employmentPattern(EmploymentPattern.valueOf(employmentPattern))
                .workingArea(workingArea)
                .build();
    }
}
