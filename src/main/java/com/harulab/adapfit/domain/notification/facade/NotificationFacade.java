package com.harulab.adapfit.domain.notification.facade;

import com.harulab.adapfit.domain.notification.domain.Notification;
import com.harulab.adapfit.domain.notification.domain.repository.NotificationRepository;
import com.harulab.adapfit.domain.notification.exception.NotificationNotFoundException;
import com.harulab.adapfit.domain.notification.presentation.dto.res.NotificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */

@Component
@RequiredArgsConstructor
public class NotificationFacade {

    private final NotificationRepository notificationRepository;

    public void send(Notification notification) {
        notificationRepository.save(notification);
    }

    public Notification getDetail(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }
}
