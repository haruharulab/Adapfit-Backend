package com.harulab.adapfit.domain.log.presentation;

import com.harulab.adapfit.domain.log.presentation.dto.req.LogCreateRequestDto;
import com.harulab.adapfit.domain.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */

@RequiredArgsConstructor
@RequestMapping("/log")
@RestController
public class LogController {

    private final LogService logService;

    @PostMapping
    public void save(
            @RequestBody LogCreateRequestDto req,
            @RequestHeader(name = "Authorization") String token
            ) {
        logService.save(req, token);
    }
}
