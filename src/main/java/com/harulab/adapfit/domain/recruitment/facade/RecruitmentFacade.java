package com.harulab.adapfit.domain.recruitment.facade;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.repository.RecruitmentRepository;
import com.harulab.adapfit.domain.recruitment.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecruitmentFacade {

    private final RecruitmentRepository recruitmentRepository;

    public void create(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    public Recruitment findByRecruitId(Long recruitId) {
        return recruitmentRepository.findById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }
}