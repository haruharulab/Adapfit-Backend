package com.harulab.adapfit.domain.resume.facade;

import com.harulab.adapfit.domain.resume.domain.Resume;
import com.harulab.adapfit.domain.resume.domain.repository.ResumeRepository;
import com.harulab.adapfit.domain.resume.exception.ResumeNotFoundException;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeDetailResponseDto;
import com.harulab.adapfit.domain.resume.presentation.dto.res.ResumeResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ResumeFacade {

    private final ResumeRepository resumeRepository;
    private final S3Uploader s3Uploader;

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    public Resume getDetail(Long resumeId) {
        return resumeRepository.findById(resumeId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }

    public void submitResume(Resume resume) {
        resumeRepository.save(resume);
    }

    public S3FileResponseDto uploadFile(MultipartFile file) throws IOException {
        return s3Uploader.saveFile(file);
    }

    public Resume findByApplyId(Long applyId) {
        return resumeRepository.findById(applyId)
                .orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
    }
}
