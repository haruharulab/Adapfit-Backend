package com.harulab.adapfit.domain.resume.service;

import com.harulab.adapfit.domain.resume.domain.Resume;
import com.harulab.adapfit.domain.resume.facade.ResumeFacade;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeRequestDto;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.facade.RecruitmentFacade;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeDetailResponseDto;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.global.utils.JwtUtil;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ResumeService {

    private final ResumeFacade resumeFacade;
    private final RecruitmentFacade recruitmentFacade;
    private final JwtUtil jwtUtil;

    public List<ResumeResponseDto> getResumes() {
        return resumeFacade.findAllByDateDesc()
                .stream()
                .map(ResumeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResumeDetailResponseDto getResume(Long resumeId, String token) {
        Resume resume = resumeFacade.getDetail(resumeId);
        String authority = jwtUtil.extractAuthorityFromToken(token);
        if (authority.equals("ROOT")) {
            resume.updateSaw();
        }
        return new ResumeDetailResponseDto(resume);
    }

    @Transactional
    public void submit(ResumeRequestDto req) throws IOException {
        Recruitment recruitment = recruitmentFacade.findByRecruitId(req.getRecruitmentId());

        Resume resumeReq = req.toEntity(submitOptionFiles(req));
        resumeReq.confirmRecruitment(recruitment);

        resumeFacade.submitResume(resumeReq);
    }

    public Map<String, String> submitOptionFiles(ResumeRequestDto req) throws IOException {
        Map<String, String> files = new HashMap<>();
        files.put("resume", resumeFacade.uploadFile(req.getResume()).getFileUrl());
        if (!req.getPortfolio().isEmpty()) {
            files.put("portfolio", resumeFacade.uploadFile(req.getPortfolio()).getFileUrl());
        } else {
            files.put("portfolio", "none");
        }

        if (!req.getEtcFile().isEmpty()) {
            files.put("etcFile", resumeFacade.uploadFile(req.getEtcFile()).getFileUrl());
        } else {
            files.put("portfolio", "none");
        }
        return files;
    }

}
