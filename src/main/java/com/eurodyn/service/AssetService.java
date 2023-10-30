package com.eurodyn.service;

import org.springframework.stereotype.Service;

@Service
public interface AssetService {
    void saveAsset(String id, String value);

}
