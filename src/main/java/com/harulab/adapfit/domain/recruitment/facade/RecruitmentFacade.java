package com.harulab.adapfit.domain.recruitment.facade;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecruitmentFacade {

    private final RecruitmentRepository recruitmentRepository;

    public void create(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }
}
