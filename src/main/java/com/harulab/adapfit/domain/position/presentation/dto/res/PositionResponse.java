package com.harulab.adapfit.domain.position.presentation.dto.res;

import com.harulab.adapfit.domain.position.domain.Position;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */

@Getter
public class PositionResponse {

    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String position;

    public PositionResponse(Position position) {
        this.id = position.getId();
        this.createdAt = position.getCreatedAt();
        this.updatedAt = position.getModifiedAt();
        this.position = position.getPosition();
    }
}
