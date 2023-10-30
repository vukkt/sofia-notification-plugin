package com.eurodyn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author vuktopalovic
 * @created 23/08/2023 - 11:09
 * @project sofia-plugin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LoginDto {

    private String username;
    private String password;

}