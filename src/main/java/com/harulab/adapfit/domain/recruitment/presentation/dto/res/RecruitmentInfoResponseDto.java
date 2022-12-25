package com.harulab.adapfit.domain.recruitment.presentation.dto.res;

import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import com.harulab.adapfit.domain.recruitment.domain.type.Position;
import lombok.Getter;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 24.
 */

@Getter
public class RecruitmentInfoResponseDto {

    private final List<Position> positionList;
    private final List<String> careerList;
    private final List<String> patternList;

    public RecruitmentInfoResponseDto(
            List<Position> positionList,
            List<String> careerList,
            List<String> patternList
    ) {
        this.positionList = positionList;
        this.careerList = careerList;
        this.patternList = patternList;
    }
}
