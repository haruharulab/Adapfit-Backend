package com.harulab.adapfit.domain.recruitment.presentation;

import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentCreateRequestDto;
import com.harulab.adapfit.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping
    public void createRecruit(@RequestBody @Valid RecruitmentCreateRequestDto req) {
        recruitmentService.createRecruitment(req);
    }

}
