package com.example.comerciantes_backend.dto;

import jakarta.validation.constraints.*;

import java.sql.Date;

public class ComercianteDTO {
    
    @NotBlank(message = "El nombre o razón social es obligatorio.")
    private String nombreRazonSocial;

    @NotBlank(message = "El municipio es obligatorio.")
    private String municipio;

    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos numéricos.")
    private String telefono;

    @Email(message = "El correo electrónico no tiene un formato válido.")
    private String correoElectronico;

    @NotNull(message = "La fecha de registro es obligatoria.")
    private Date fechaRegistro;

    @NotBlank(message = "El estado es obligatorio.")
    private String estado;

    // Getters y Setters
    public String getNombreRazonSocial() { return nombreRazonSocial; }
    public void setNombreRazonSocial(String nombreRazonSocial) { this.nombreRazonSocial = nombreRazonSocial; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
