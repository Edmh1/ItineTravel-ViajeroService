package com.zapaticorp.viajero_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombreUsuario;
    private String apellidoUsuario;
    private Integer telefono;
    private String email;
    private String clave;
}
