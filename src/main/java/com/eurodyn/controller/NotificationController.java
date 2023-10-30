package com.eurodyn.controller;

import com.eurodyn.dto.NotificationDto;
import com.eurodyn.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @PostMapping("/notification")
    public ResponseEntity<Map<String,String>> createNotification(@RequestBody NotificationDto notificationDto) {
        try {
            String message = notificationService.retrieveMessage(notificationDto);
            notificationDto.setMessage(message);

            String id = notificationService.saveNotification(notificationDto);
            return ResponseEntity.ok(Collections.singletonMap("notification_id", id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
