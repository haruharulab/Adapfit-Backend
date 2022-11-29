package com.harulab.adapfit.domain.recruitment.presentation.dto.req;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import com.harulab.adapfit.domain.recruitment.domain.type.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecruitmentCreateRequestDto {

    private String title;
    private String content;
    private String group;
    private Integer career;
    private String employmentPattern;
    private String workingArea;

    @Builder
    public RecruitmentCreateRequestDto(String title, String content, String group, Integer career, String employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.group = group;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }

    public Recruitment toEntity() {
        return Recruitment.builder()
                .title(title)
                .content(content)
                .group(Group.valueOf(group))
                .career(career)
                .employmentPattern(EmploymentPattern.valueOf(employmentPattern))
                .workingArea(workingArea)
                .build();
    }
}
