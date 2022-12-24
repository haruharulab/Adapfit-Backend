package com.harulab.adapfit.domain.recruitment.service;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import com.harulab.adapfit.domain.recruitment.domain.type.Position;
import com.harulab.adapfit.domain.recruitment.facade.RecruitmentFacade;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentCreateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentInfoResponseDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class RecruitmentService {

    private final RecruitmentFacade recruitmentFacade;

    @Transactional
    public void createRecruitment(RecruitmentCreateRequestDto req) {
        recruitmentFacade.create(req.toEntity());
    }

    public List<RecruitmentResponseDto> getRecruit(String position, String career, String employmentPattern) {
        return recruitmentFacade.findRecruitByDynamicQuery(position, career, employmentPattern)
                .stream()
                .map(RecruitmentResponseDto::new)
                .collect(Collectors.toList());
    }

    public RecruitmentResponseDto detail(Long recruitmentId) {
        return new RecruitmentResponseDto(recruitmentFacade.findByRecruitId(recruitmentId));
    }

    @Transactional
    public void updateRecruitment(Long recruitId, RecruitmentUpdateRequestDto req) {
        Recruitment recruitment = recruitmentFacade.findByRecruitId(recruitId);
        recruitment.updateInfo(req);
    }

    @Transactional
    public void deleteRecruitment(Long recruitId) {
        recruitmentFacade.deleteByRecruitId(recruitId);
    }

    public RecruitmentInfoResponseDto getInformation() {
        List<Position> positions = Arrays.asList(Position.values());
        List<String> careers = List.of("신입", "경력");
        List<EmploymentPattern> employmentPatterns = Arrays.asList(EmploymentPattern.values());
        return new RecruitmentInfoResponseDto(positions, careers, employmentPatterns);
    }
}
