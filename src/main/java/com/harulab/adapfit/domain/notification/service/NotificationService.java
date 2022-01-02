package com.harulab.adapfit.domain.notification.service;

import com.harulab.adapfit.domain.notification.domain.Notification;
import com.harulab.adapfit.domain.notification.facade.NotificationFacade;
import com.harulab.adapfit.domain.notification.presentation.dto.req.NotificationCreateRequest;
import com.harulab.adapfit.domain.notification.presentation.dto.res.NotificationResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationFacade notificationFacade;

    @Transactional
    public void create(NotificationCreateRequest req) {
        notificationFacade.send(req.toEntity());
    }

    @Transactional
    public void changeStatus(Long notificationId) {
        Notification notification = notificationFacade.getDetail(notificationId);
        notification.changeSaw();
    }

    public List<NotificationResponseDto> getList() {
        return notificationFacade.getAll()
                .stream()
                .map(NotificationResponseDto::new)
                .collect(Collectors.toList());
    }
}
