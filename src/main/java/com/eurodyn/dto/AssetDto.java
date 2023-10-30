package com.eurodyn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AssetDto {
    private String asset_id;
    private String image_base64;
    private MultipartFile image;
}
