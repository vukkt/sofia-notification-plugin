package com.eurodyn.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void saveNotification(String title, String value, String asset_id);
}
