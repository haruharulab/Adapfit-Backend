package com.harulab.adapfit.domain.recruitment.service;

import com.harulab.adapfit.domain.log.service.LogService;
import com.harulab.adapfit.domain.position.domain.Position;
import com.harulab.adapfit.domain.position.facade.PositionFacade;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.recruitment.facade.RecruitmentFacade;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentCreateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentInfoResponseDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class RecruitmentService {

    private final RecruitmentFacade recruitmentFacade;
    private final LogService logService;
    private final PositionFacade positionFacade;

    @Transactional
    public void createRecruitment(RecruitmentCreateRequestDto req, String token) {
        Position position = positionFacade.findByPosition(req.getPosition());

        Recruitment recruitment = req.toEntity();
        recruitment.confirmPosition(position);
        recruitmentFacade.create(recruitment);

        logService.save(req.getTitle() + " 채용공고를 생성하였습니다.", token);
    }

    public List<RecruitmentResponseDto> getRecruit(String positionName, String career, String employmentPattern) {
        Position position = null;
        if (positionName.length() != 0) position = positionFacade.findByPosition(positionName);
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
        Position position = positionFacade.findByPosition(req.getPosition());
        recruitment.updateInfo(req);
        recruitment.confirmPosition(position);
        logService.save(req.getTitle() + " 채용공고를 수정하였습니다.", token);
    }

    @Transactional
    public void deleteRecruitment(Long recruitId, String token) {
        Recruitment recruit = recruitmentFacade.findByRecruitId(recruitId);
        logService.save(recruit.getTitle() + " 채용공고를 삭제하였습니다.", token);
        recruitmentFacade.deleteByRecruitId(recruitId);
    }

    public RecruitmentInfoResponseDto getInformation() {
        List<Position> positions = positionFacade.findAll();
        List<String> careers = List.of("신입", "경력", "상관없음");
        List<String> employmentPatterns = List.of("정규직", "비정규직");
        return new RecruitmentInfoResponseDto(positions, careers, employmentPatterns);
    }
}
