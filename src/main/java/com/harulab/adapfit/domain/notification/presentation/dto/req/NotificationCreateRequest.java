package com.harulab.adapfit.domain.notification.presentation.dto.req;

import com.harulab.adapfit.domain.notification.domain.Notification;
import com.harulab.adapfit.domain.notification.domain.type.Saw;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */

@Getter
public class NotificationCreateRequest {

    private final String prefix;
    private final String content;
    private final Long resumeId;

    public NotificationCreateRequest(String prefix, String content, Long resumeId) {
        this.prefix = prefix;
        this.content = content;
        this.resumeId = resumeId;
    }

    public Notification toEntity() {
        return Notification.builder()
                .prefix(prefix)
                .content(content)
                .resumeId(resumeId)
                .saw(Saw.NOT_READ)
                .build();
    }
}
