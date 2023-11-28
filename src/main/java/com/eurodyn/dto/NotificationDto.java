package com.eurodyn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NotificationDto {
    private String title;
    private String description;
    private String message;
    private String asset_id;
    private String threat_category_id;
}
