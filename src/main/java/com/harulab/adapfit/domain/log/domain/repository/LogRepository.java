package com.harulab.adapfit.domain.log.domain.repository;

import com.harulab.adapfit.domain.log.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */
public interface LogRepository extends JpaRepository<Log, Long> {
}
