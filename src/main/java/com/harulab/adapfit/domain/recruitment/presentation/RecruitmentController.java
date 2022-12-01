package com.harulab.adapfit.domain.recruitment.presentation;

import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentCreateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.presentation.dto.res.RecruitmentResponseDto;
import com.harulab.adapfit.domain.recruitment.service.RecruitmentService;
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
    public List<RecruitmentResponseDto> searchRecruit(
            @RequestParam("jobGroup") String jobGroup,
            @RequestParam("career") Integer career,
            @RequestParam("employmentPattern") String employmentPattern
    ) {
        return recruitmentService.getRecruit(jobGroup, career, employmentPattern);
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
