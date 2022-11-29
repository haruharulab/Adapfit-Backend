package com.harulab.adapfit.domain.resume.presentation;

import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeRequestDto;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeUpdateRequestDto;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ProxyResumeRequestDto;
import com.harulab.adapfit.domain.resume.presentation.dto.req.ProxyResumeUpdateRequestDto;
import com.harulab.adapfit.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RequestMapping("/resume")
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public void submit(
            @RequestPart(value = "req") @Valid ProxyResumeRequestDto req,
            @RequestPart (value = "file") MultipartFile file
            ) throws IOException {
        resumeService.submit(new ResumeRequestDto(req, file));
    }

    @PutMapping
    public void update(
            @RequestPart(value = "req") @Valid ProxyResumeUpdateRequestDto req,
            @RequestPart (value = "file") MultipartFile file
    ) throws IOException {
        resumeService.update(new ResumeUpdateRequestDto(req, file));
    }

}