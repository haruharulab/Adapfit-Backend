package com.harulab.adapfit.domain.apply.presentation;

import com.harulab.adapfit.domain.apply.presentation.dto.req.ApplySubmitRequestDto;
import com.harulab.adapfit.domain.apply.presentation.dto.req.ApplyUpdateRequestDto;
import com.harulab.adapfit.domain.apply.presentation.dto.req.ProxyApplyRequestDto;
import com.harulab.adapfit.domain.apply.presentation.dto.req.ProxyApplyUpdateRequestDto;
import com.harulab.adapfit.domain.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RequestMapping("/apply")
@RestController
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    public void submit(
            @RequestPart(value = "req") @Valid ProxyApplyRequestDto req,
            @RequestPart (value = "file") MultipartFile file
            ) throws IOException {
        applyService.submit(new ApplySubmitRequestDto(req, file));
    }

    @PutMapping
    public void update(
            @RequestPart(value = "req") @Valid ProxyApplyUpdateRequestDto req,
            @RequestPart (value = "file") MultipartFile file
    ) throws IOException {
        applyService.update(new ApplyUpdateRequestDto(req, file));
    }
}
