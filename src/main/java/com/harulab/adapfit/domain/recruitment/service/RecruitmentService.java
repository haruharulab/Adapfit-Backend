package com.harulab.adapfit.domain.recruitment.service;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.facade.RecruitmentFacade;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentCreateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentUpdateRequestDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class RecruitmentService {

    private final RecruitmentFacade recruitmentFacade;

    @Transactional
    public void createRecruitment(RecruitmentCreateRequestDto req) {
        recruitmentFacade.create(req.toEntity());
    }

    @Transactional
    public void updateRecruitment(Long recruitId, RecruitmentUpdateRequestDto req) {
        Recruitment recruitment = recruitmentFacade.findByRecruitId(recruitId);
        recruitment.updateInfo(req);
    }
}
