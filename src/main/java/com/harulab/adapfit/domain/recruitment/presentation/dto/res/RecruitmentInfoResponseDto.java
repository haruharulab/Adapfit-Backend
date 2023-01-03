package com.harulab.adapfit.domain.recruitment.presentation.dto.res;

import com.harulab.adapfit.domain.position.domain.Position;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 24.
 */

@Getter
public class RecruitmentInfoResponseDto {

    private final List<String> positionList;
    private final List<String> careerList;
    private final List<String> patternList;

    public RecruitmentInfoResponseDto(
            List<Position> positionList,
            List<String> careerList,
            List<String> patternList
    ) {
        this.positionList = positionList.stream().map(Position::getPosition)
                .collect(Collectors.toList());
        this.careerList = careerList;
        this.patternList = patternList;
    }
}
