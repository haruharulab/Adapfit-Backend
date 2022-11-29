package com.harulab.adapfit.domain.apply.service;

import com.harulab.adapfit.domain.apply.domain.Apply;
import com.harulab.adapfit.domain.apply.facade.ApplyFacade;
import com.harulab.adapfit.domain.apply.presentation.dto.req.ApplySubmitRequestDto;
import com.harulab.adapfit.domain.apply.presentation.dto.req.ApplyUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.facade.RecruitmentFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ApplyService {

    private final ApplyFacade applyFacade;
    private final RecruitmentFacade recruitmentFacade;

    @Transactional
    public void submit(ApplySubmitRequestDto req) throws IOException {
        S3FileResponseDto fileDto = applyFacade.uploadFile(req.getFile());
        Recruitment recruitment = recruitmentFacade.findByRecruitId(req.getRecruitmentId());

        Apply applyReq = req.toEntity(fileDto);
        applyReq.confirmRecruitment(recruitment);

        applyFacade.submitApply(applyReq);
    }

    @Transactional
    public void update(ApplyUpdateRequestDto req) throws IOException {
        Apply apply = applyFacade.findByApplyId(req.getApplyId());
        S3FileResponseDto fileDto = applyFacade.uploadFile(req.getFile());
        apply.updateInfo(req, fileDto);
    }
}
