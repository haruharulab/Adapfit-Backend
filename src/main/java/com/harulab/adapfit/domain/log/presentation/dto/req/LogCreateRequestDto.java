package com.harulab.adapfit.domain.log.presentation.dto.req;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.log.domain.Log;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */

@Getter
public class LogCreateRequestDto {

    private final String message;

    public LogCreateRequestDto(String message) {
        this.message = message;
    }

    public Log toEntity(String nickname) {
        return Log.builder()
                .nickname(nickname)
                .message(message)
                .didAt(LocalDateTime.now())
                .build();
    }
}
