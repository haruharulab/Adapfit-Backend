package com.harulab.adapfit.domain.recruitment.domain.repository;

import com.harulab.adapfit.domain.position.domain.Position;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;

import java.util.List;

public interface RecruitmentCustomRepository {

    List<Recruitment> searchRecruitment(Position position, String career, String employmentPattern);

}
