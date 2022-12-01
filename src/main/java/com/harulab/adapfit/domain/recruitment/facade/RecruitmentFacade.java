package com.harulab.adapfit.domain.recruitment.facade;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.repository.RecruitmentRepository;
import com.harulab.adapfit.domain.recruitment.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void deleteByRecruitId(Long recruitId) {
        recruitmentRepository.deleteById(recruitId);
    }

    public List<Recruitment> findRecruitByDynamicQuery(String jobGroup, String career, String employmentPattern) {
        return recruitmentRepository.searchRecruitment(jobGroup, career, employmentPattern);
    }
}
