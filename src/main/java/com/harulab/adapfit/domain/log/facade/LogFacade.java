package com.harulab.adapfit.domain.log.facade;

import com.harulab.adapfit.domain.log.domain.Log;
import com.harulab.adapfit.domain.log.domain.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */

@RequiredArgsConstructor
@Component
public class LogFacade {

    private final LogRepository logRepository;

    public void save(Log log) {
        logRepository.save(log);
    }

    public List<Log> findAllOrderByDateAtDesc() {
        return logRepository.findAllOrderByDateAtDesc();
    }

    public void removeAll() {
        logRepository.deleteAll();
    }
}
