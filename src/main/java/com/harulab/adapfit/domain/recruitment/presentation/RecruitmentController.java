package com.harulab.adapfit.domain.recruitment.presentation;

import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentCreateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentResponseDto;
import com.harulab.adapfit.domain.recruitment.service.RecruitmentService;
import com.harulab.adapfit.global.generic.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping
    public ResultResponse<List<RecruitmentResponseDto>> searchRecruit(
            @RequestParam("position") String position,
            @RequestParam("career") String career,
            @RequestParam("employmentPattern") String employmentPattern
    ) {
        List<RecruitmentResponseDto> recruits = recruitmentService.getRecruit(position, career, employmentPattern);
        return new ResultResponse<>(recruits.size(), recruits);
    }

    @GetMapping("/{recruitmentId}")
    public RecruitmentResponseDto getDetail(@PathVariable Long recruitmentId) {
        return recruitmentService.detail(recruitmentId);
    }

    @PostMapping
    public void createRecruit(@RequestBody @Valid RecruitmentCreateRequestDto req) {
        recruitmentService.createRecruitment(req);
    }

    @PutMapping("/{recruitId}")
    public void updateRecruit(
            @PathVariable Long recruitId,
            @RequestBody @Valid RecruitmentUpdateRequestDto req
    ) {
        recruitmentService.updateRecruitment(recruitId, req);
    }

    @DeleteMapping("/{recruitId}")
    public void deleteRecruit(@PathVariable Long recruitId) {
        recruitmentService.deleteRecruitment(recruitId);
    }
}
