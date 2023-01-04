package com.harulab.adapfit.domain.position.presentation;

import com.harulab.adapfit.domain.position.presentation.dto.req.PositionCreateRequest;
import com.harulab.adapfit.domain.position.presentation.dto.res.PositionResponse;
import com.harulab.adapfit.domain.position.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */

@RestController
@RequestMapping("/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public void create(@RequestBody PositionCreateRequest req) {
        positionService.create(req);
    }

    @GetMapping
    public ResponseEntity<List<PositionResponse>> getAll() {
        List<PositionResponse> positionResponses = positionService.searchAll();
        return ResponseEntity.ok(positionResponses);
    }

    @PutMapping("/{positionId}")
    public void update(
            @PathVariable Long positionId,
            @RequestParam String position
    ) {
        positionService.update(positionId, position);
    }
}
