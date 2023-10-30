package com.eurodyn.controller;

import com.eurodyn.dto.NotificationDto;
import com.eurodyn.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @PostMapping("/notification")
    public ResponseEntity<String> createNotification(@RequestBody NotificationDto notificationDto) {
        try {
            String title = notificationDto.getTitle();
            String message = notificationDto.getMessage();
            String asset_id = notificationDto.getAsset_id();
            notificationService.saveNotification(title, message, asset_id);
            return ResponseEntity.ok("Notification created succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("failed to create notification: " + e.getMessage()));
        }
    }
}
