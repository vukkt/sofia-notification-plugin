package com.eurodyn.controller;

import com.eurodyn.dto.AssetDto;
import com.eurodyn.service.impl.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssetController {

    @Autowired
    private AssetServiceImpl assetService;

    @PostMapping("/asset")
    public ResponseEntity<String> createAsset(@RequestBody AssetDto assetDto) {
        try {
            String id = assetDto.getAsset_id();
            String imageBase64 = assetDto.getImage_base64();
            assetService.saveAsset(id, imageBase64);
            return ResponseEntity.ok("Asset created succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("failed to create asset: " + e.getMessage()));
        }
    }
}
