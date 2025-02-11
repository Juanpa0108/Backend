package com.example.comerciantes_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String correoElectronico;
    private String contrasena;
}
