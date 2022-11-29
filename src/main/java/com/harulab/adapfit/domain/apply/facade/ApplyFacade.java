package com.harulab.adapfit.domain.apply.facade;

import com.harulab.adapfit.domain.apply.domain.Apply;
import com.harulab.adapfit.domain.apply.domain.repository.ApplyRepository;
import com.harulab.adapfit.domain.apply.exception.ApplyNotFoundException;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import com.harulab.adapfit.infrastructure.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ApplyFacade {

    private final ApplyRepository applyRepository;
    private final S3Uploader s3Uploader;

    public void submitApply(Apply apply) {
        applyRepository.save(apply);
    }

    public S3FileResponseDto uploadFile(MultipartFile file) throws IOException {
        return s3Uploader.saveFile(file);
    }

    public Apply findByApplyId(Long applyId) {
        return applyRepository.findById(applyId)
                .orElseThrow(() -> ApplyNotFoundException.EXCEPTION);
    }
}
