package com.eurodyn.service.impl;

import com.eurodyn.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public void saveNotification(String title, String value, String assetId) {
        Query query = entityManager.createNativeQuery("INSERT INTO notification (title, message,asset_id) VALUES (:title, :value,:asset_id)");
        query.setParameter("title", title);
        query.setParameter("value", value);
        query.setParameter("asset_id", assetId);
        query.executeUpdate();
    }

}

