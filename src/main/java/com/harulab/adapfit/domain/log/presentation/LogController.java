package com.harulab.adapfit.domain.log.presentation;

import com.harulab.adapfit.domain.log.presentation.dto.res.LogResponse;
import com.harulab.adapfit.domain.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 30.
 */

@RequestMapping("/log")
@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping
    public ResponseEntity<List<LogResponse>> getAll() {
        List<LogResponse> logs = logService.searchAll();
        return ResponseEntity.ok(logs);
    }
}
