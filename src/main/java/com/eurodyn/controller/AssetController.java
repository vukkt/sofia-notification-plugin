package com.eurodyn.controller;

import com.eurodyn.dto.AssetDto;
import com.eurodyn.service.impl.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AssetController {

    static final List<String> allowedTypes = Arrays.asList("image/jpeg", "image/jpg", "image/png");

    @Autowired
    private AssetServiceImpl assetService;

    @PostMapping(path = "asset-image",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Map<String,String>> createAssetImage(
            @ModelAttribute AssetDto assetDto,
            @RequestHeader Map<String, String> headers) throws IOException {

        if (assetDto.getImage().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","empty file"));
        }

        if (!allowedTypes.contains(assetDto.getImage().getContentType())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","jpg, jpeg, or png allowed"));
        }

       return assetService.createAssetImage(assetDto, headers);
    }

}
