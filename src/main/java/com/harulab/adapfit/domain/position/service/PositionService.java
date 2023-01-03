package com.harulab.adapfit.domain.position.service;

import com.harulab.adapfit.domain.position.facade.PositionFacade;
import com.harulab.adapfit.domain.position.presentation.dto.req.PositionCreateRequest;
import com.harulab.adapfit.domain.position.presentation.dto.res.PositionResponse;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class PositionService {

    private final PositionFacade positionFacade;

    @Transactional
    public void create(PositionCreateRequest req) {
        positionFacade.save(req.toEntity());
    }

    public List<PositionResponse> searchAll() {
        return positionFacade.findAll().stream()
                .map(PositionResponse::new)
                .collect(Collectors.toList());
    }
}
