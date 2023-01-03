package com.harulab.adapfit.domain.position.presentation.dto.req;

import com.harulab.adapfit.domain.position.domain.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */

@Getter
@NoArgsConstructor
public class PositionCreateRequest {

    private String position;

    public PositionCreateRequest(String position) {
        this.position = position;
    }

    public Position toEntity() {
        return new Position(position);
    }
}
