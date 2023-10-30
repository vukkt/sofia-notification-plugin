package com.eurodyn.service.impl;

import com.eurodyn.dto.AssetDto;
import com.eurodyn.service.AssetService;
import com.eurodyn.utils.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService {

    static final int newImageWidth = 200;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public ResponseEntity<Map<String, String>> createAssetImage(AssetDto assetDto) throws IOException {
        try {
            String fileType = assetDto.getImage().getContentType();

            // Read the content of the file
            byte[] fileBytes = IOUtils.toByteArray(assetDto.getImage().getInputStream());
            String imageBase64 = Base64.getEncoder().encodeToString(fileBytes);

            String fileName = assetDto.getImage().getOriginalFilename();

            String minimizedImage = ImageUtils.minimizeImage(imageBase64, newImageWidth);

            String imageId = this.saveAssetImage(assetDto.getAsset_id(), "data:" + fileType + ";base64," + minimizedImage, fileName, fileType);
            this.saveAssetOriginalImage(imageId, "data:" + fileType + ";base64," + imageBase64, fileName, fileType);

            return ResponseEntity.ok(Collections.singletonMap("asset_image_id", imageId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public String saveAssetImage(String asset_id, String value,String filename,String fileType) {
        Query query = entityManager.createNativeQuery("INSERT INTO asset_image (asset_id, image, filename, filetype) VALUES (:asset_id, :value, :filename, :filetype)");
        query.setParameter("asset_id", asset_id);
        query.setParameter("value", value);
        query.setParameter("filename", filename);
        query.setParameter("filetype", fileType);
        query.executeUpdate();

        return entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult().toString();
    }

    @Transactional
    @Override
    public void saveAssetOriginalImage(String id, String value,String filename,String fileType) {
        Query query = entityManager.createNativeQuery("INSERT INTO asset_original_image (asset_image_id, image, filename, filetype) VALUES (:asset_image_id, :value, :filename, :filetype)");
        query.setParameter("asset_image_id", id);
        query.setParameter("value", value);
        query.setParameter("filename", filename);
        query.setParameter("filetype", fileType);
        query.executeUpdate();
    }


}

