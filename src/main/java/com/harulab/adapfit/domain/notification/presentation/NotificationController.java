package com.harulab.adapfit.domain.notification.presentation;

import com.harulab.adapfit.domain.notification.presentation.dto.res.NotificationResponseDto;
import com.harulab.adapfit.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 23.
 */

@RequestMapping("/notification")
@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @PutMapping("/{notificationId}")
    public void changeStatus(@PathVariable Long notificationId) {
        notificationService.changeStatus(notificationId);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getAll() {
        List<NotificationResponseDto> notifications = notificationService.getList();
        return ResponseEntity.ok(notifications);
    }
}
