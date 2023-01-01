package com.harulab.adapfit.domain.log.domain.repository;

import com.harulab.adapfit.domain.log.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("select l from Log l order by l.didAt desc")
    List<Log> findAllOrderByDateAtDesc();
}
