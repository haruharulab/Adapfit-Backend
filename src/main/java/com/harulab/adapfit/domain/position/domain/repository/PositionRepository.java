package com.harulab.adapfit.domain.position.domain.repository;

import com.harulab.adapfit.domain.position.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 03.
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select p from Position p where p.position = :position")
    Optional<Position> findByPosition(@Param("position") String position);
}
