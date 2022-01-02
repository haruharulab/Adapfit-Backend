package com.harulab.adapfit.domain.notification.presentation.dto.res;

import com.harulab.adapfit.domain.notification.domain.Notification;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */

@Getter
public class NotificationResponseDto {

    private final Long id;
    private final String content;
    private final String prefix;
    private final Long resumeId;
    private final String saw;

    public NotificationResponseDto(Notification notification) {
        this.id = notification.getId();
        this.content = notification.getContent();
        this.prefix = notification.getPrefix();
        this.resumeId = notification.getResumeId();
        this.saw = notification.getSaw().name();
    }
}
