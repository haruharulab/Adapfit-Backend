package com.harulab.adapfit.domain.resume.service;

import com.harulab.adapfit.domain.resume.domain.Resume;
import com.harulab.adapfit.domain.resume.facade.ResumeFacade;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeRequestDto;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.facade.RecruitmentFacade;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeDetailResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ResumeService {

    private final ResumeFacade resumeFacade;
    private final RecruitmentFacade recruitmentFacade;

    public ResumeDetailResponseDto getResume(Long resumeId) {
        return new ResumeDetailResponseDto(resumeFacade.getDetail(resumeId));
    }

    @Transactional
    public void submit(ResumeRequestDto req) throws IOException {
        S3FileResponseDto fileDto = resumeFacade.uploadFile(req.getFile());
        Recruitment recruitment = recruitmentFacade.findByRecruitId(req.getRecruitmentId());

        Resume resumeReq = req.toEntity(fileDto);
        resumeReq.confirmRecruitment(recruitment);

        resumeFacade.submitResume(resumeReq);
    }

    @Transactional
    public void update(ResumeUpdateRequestDto req) throws IOException {
        Resume resume = resumeFacade.findByApplyId(req.getApplyId());
        S3FileResponseDto fileDto = resumeFacade.uploadFile(req.getFile());
        resume.updateInfo(req, fileDto);
    }

}
