package com.zapaticorp.viajero_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeroResponse {
    private Integer id;
    private String email;
}
