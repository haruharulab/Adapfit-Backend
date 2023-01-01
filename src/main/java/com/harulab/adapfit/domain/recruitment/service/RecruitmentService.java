package com.harulab.adapfit.domain.recruitment.service;

import com.harulab.adapfit.domain.log.service.LogService;
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
    private final LogService logService;

    @Transactional
    public void createRecruitment(RecruitmentCreateRequestDto req, String token) {
        recruitmentFacade.create(req.toEntity());
        logService.save(req.getTitle() + " 채용공고를 생성하였습니다.", token);
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
    public void updateRecruitment(Long recruitId, RecruitmentUpdateRequestDto req, String token) {
        Recruitment recruitment = recruitmentFacade.findByRecruitId(recruitId);
        recruitment.updateInfo(req);
        logService.save(req.getTitle() + " 채용공고를 수정하였습니다.", token);
    }

    @Transactional
    public void deleteRecruitment(Long recruitId, String token) {
        Recruitment recruit = recruitmentFacade.findByRecruitId(recruitId);
        logService.save(recruit.getTitle() + " 채용공고를 삭제하였습니다.", token);
        recruitmentFacade.deleteByRecruitId(recruitId);
    }

    public RecruitmentInfoResponseDto getInformation() {
        List<Position> positions = Arrays.asList(Position.values());
        List<String> careers = List.of("신입", "경력", "상관없음");
        List<String> employmentPatterns = List.of("정규직", "비정규직");
        return new RecruitmentInfoResponseDto(positions, careers, employmentPatterns);
    }
}
