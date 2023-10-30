package com.eurodyn.service.impl;

import com.eurodyn.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class AssetServiceImpl implements AssetService {


    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void saveAsset(String id, String value) {
        Query query = entityManager.createNativeQuery("INSERT INTO asset_image (asset_id, image) VALUES (:asset_id, :value)");
        query.setParameter("asset_id", id);
        query.setParameter("value", value);
        query.executeUpdate();
    }

}

