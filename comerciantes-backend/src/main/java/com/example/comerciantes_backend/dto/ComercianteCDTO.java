package com.example.comerciantes_backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ComercianteCDTO {
    private Long id;
    private String nombreRazonSocial;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private Date fechaRegistro;
    private String estado;
    private Date fechaActualizacion;
    private String usuarioActualizacion;
    private Long cantidadEstablecimientos; 

    public ComercianteCDTO(Long id, String nombreRazonSocial, String municipio, String telefono, 
                          String correoElectronico, Date fechaRegistro, String estado, 
                          Date fechaActualizacion, String usuarioActualizacion, Long cantidadEstablecimientos) {
        this.id = id;
        this.nombreRazonSocial = nombreRazonSocial;
        this.municipio = municipio;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.cantidadEstablecimientos = cantidadEstablecimientos;
    }
}
