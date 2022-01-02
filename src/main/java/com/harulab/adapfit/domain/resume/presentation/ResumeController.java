package com.harulab.adapfit.domain.resume.presentation;

import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeRequestDto;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ProxyResumeRequestDto;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeDetailResponseDto;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeResponseDto;
import com.harulab.adapfit.domain.resume.service.ResumeService;
import com.harulab.adapfit.global.generic.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/resume")
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping
    public ResultResponse<List<ResumeResponseDto>> getAll() {
        List<ResumeResponseDto> resumes = resumeService.getResumes();
        return new ResultResponse<>(resumes.size(), resumes);
    }

    @GetMapping("/{resumeId}")
    public ResumeDetailResponseDto getDetail(@PathVariable Long resumeId) {
        return resumeService.getResume(resumeId);
    }

    @PostMapping
    public void submit(
            @RequestPart(value = "req") @Valid ProxyResumeRequestDto req,
            @RequestPart(value = "resume") MultipartFile resume,
            @RequestPart(value = "portfolio", required = false) MultipartFile portfolio,
            @RequestPart(value = "etcFile", required = false) MultipartFile etcFile
            ) throws IOException {
        resumeService.submit(new ResumeRequestDto(req, resume, portfolio, etcFile));
    }

}