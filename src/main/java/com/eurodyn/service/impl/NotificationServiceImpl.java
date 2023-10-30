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
    public void saveNotification(String id, String value, String assetId) {
        Query query = entityManager.createNativeQuery("INSERT INTO notification (id, message,asset_id) VALUES (:id, :value,:asset_id)");
        query.setParameter("id", id);
        query.setParameter("value", value);
        query.setParameter("asset_id", assetId);
        query.executeUpdate();
    }

}

