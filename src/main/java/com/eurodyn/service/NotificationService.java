package com.eurodyn.service;

import com.eurodyn.dto.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    String saveNotification(NotificationDto notificationDto);
}
