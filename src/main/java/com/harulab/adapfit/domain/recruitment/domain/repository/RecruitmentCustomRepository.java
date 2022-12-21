package com.harulab.adapfit.domain.recruitment.domain.repository;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;

import java.util.List;

public interface RecruitmentCustomRepository {

    List<Recruitment> searchRecruitment(String position, String career, String employmentPattern);

}
