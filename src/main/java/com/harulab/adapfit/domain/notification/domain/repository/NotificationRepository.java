package com.harulab.adapfit.domain.notification.domain.repository;

import com.harulab.adapfit.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
