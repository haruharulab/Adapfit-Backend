package com.harulab.adapfit.domain.log.presentation.dto.res;

import com.harulab.adapfit.domain.log.domain.Log;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 30.
 */

@Getter
public class LogResponse {

    private final Long id;
    private final String nickname;
    private final String message;
    private final LocalDateTime didAt;

    public LogResponse(Log log) {
        this.id = log.getId();
        this.nickname = log.getNickname();
        this.message = log.getMessage();
        this.didAt = log.getDidAt();
    }
}
