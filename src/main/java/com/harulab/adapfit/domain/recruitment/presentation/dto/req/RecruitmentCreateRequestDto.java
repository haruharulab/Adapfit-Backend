package com.harulab.adapfit.domain.recruitment.presentation.dto.req;

import com.harulab.adapfit.domain.position.domain.Position;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.*;

@NoArgsConstructor
@Getter
public class RecruitmentCreateRequestDto {

    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = CONTENT_NOT_BLANK)
    private String content;

    @NotBlank(message = POSITION_NOT_BLANK)
    private String position;

    private String career;

    @NotBlank(message = EMPLOYMENT_PATTERN)
    private String employmentPattern;

    private String workingArea;

    @Builder
    public RecruitmentCreateRequestDto(String title, String content, String position, String career, String employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.position = position;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }

    public Recruitment toEntity(Position position) {
        return Recruitment.builder()
                .position(position)
                .title(title)
                .content(content)
                .career(career)
                .employmentPattern(EmploymentPattern.valueOf(employmentPattern))
                .workingArea(workingArea)
                .build();
    }
}
