package com.eurodyn.service.impl;

import com.eurodyn.dto.NotificationDto;
import com.eurodyn.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public String saveNotification(NotificationDto notificationDto) {
        String id = UUID.randomUUID().toString();
        Query query = entityManager.createNativeQuery("INSERT INTO notification (id, title, message,asset_id, receiver_id, threat_category_id) " +
                "VALUES (" +
                ":id, " +
                ":title, " +
                ":value, " +
                ":asset_id, " +
                " (SELECT created_by from asset WHERE id = :asset_id), " +
                ":threat_category_id" +
                " )");

        query.setParameter("id",id );
        query.setParameter("title", notificationDto.getTitle());
        query.setParameter("value", notificationDto.getMessage());
        query.setParameter("asset_id", notificationDto.getAsset_id());
        query.setParameter("threat_category_id", notificationDto.getThreat_category_id());
        query.executeUpdate();
        return id;
    }

    public String retrieveMessage(NotificationDto notificationDto) {

        String sql = "SELECT GROUP_CONCAT(CONCAT('<i class=\"fa-solid fa-circle-notch\"></i> ', s.name) SEPARATOR '<br>') AS message " +
                "FROM asset a " +
                "INNER JOIN scenario s ON s.asset_id = a.id " +
                "INNER JOIN threat t ON s.threat_id = t.id " +
                "WHERE a.id = :asset_id " +
                "AND t.threat_category_id = :threat_category_id";


        return sql;
    }
}

