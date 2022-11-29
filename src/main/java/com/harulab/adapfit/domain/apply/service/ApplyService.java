package com.harulab.adapfit.domain.apply.service;

import com.harulab.adapfit.domain.apply.facade.ApplyFacade;
import com.harulab.adapfit.domain.apply.presentation.dto.req.ApplySubmitRequestDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ApplyService {

    private final ApplyFacade applyFacade;

    @Transactional
    public void submit(ApplySubmitRequestDto req) throws IOException {
        S3FileResponseDto fileDto = applyFacade.uploadFile(req.getFile());
        applyFacade.submitApply(req.toEntity(fileDto));
    }
}
