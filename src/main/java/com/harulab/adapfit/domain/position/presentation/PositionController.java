package com.harulab.adapfit.domain.position.presentation;

import com.harulab.adapfit.domain.position.presentation.dto.req.PositionCreateRequest;
import com.harulab.adapfit.domain.position.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
