package com.eurodyn.service;

import org.springframework.stereotype.Service;

@Service
public interface AssetService {
    String saveAssetImage(String id, String imageBase64,String filename,String fileType);
    void saveAssetOriginalImage(String asset_id, String imageBase64,String filename,String fileType);
}
