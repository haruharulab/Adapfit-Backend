package com.harulab.adapfit.domain.position.facade;

import com.harulab.adapfit.domain.position.domain.Position;
import com.harulab.adapfit.domain.position.domain.repository.PositionRepository;
import com.harulab.adapfit.domain.position.exception.PositionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */

@Component
@RequiredArgsConstructor
public class PositionFacade {

    private final PositionRepository positionRepository;

    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public Position findById(Long positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> PositionNotFoundException.EXCEPTION);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findByPosition(String position) {
        return positionRepository.findByPosition(position)
                .orElseThrow(() -> PositionNotFoundException.EXCEPTION);
    }
}
