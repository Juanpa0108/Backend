package com.example.comerciantes_backend.dto;

import java.sql.Date;

public class ComercianteReporteDTO {
    private String nombreRazonSocial;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private Date fechaRegistro;
    private String estado;
    private int cantidadEstablecimientos;
    private double ingresosTotales;
    private int totalEmpleados;

    // Constructor con todos los campos
    public ComercianteReporteDTO(String nombreRazonSocial, String municipio, String telefono,
                                 String correoElectronico, Date fechaRegistro, String estado,
                                 int cantidadEstablecimientos, double ingresosTotales, int totalEmpleados) {
        this.nombreRazonSocial = nombreRazonSocial;
        this.municipio = municipio;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.cantidadEstablecimientos = cantidadEstablecimientos;
        this.ingresosTotales = ingresosTotales;
        this.totalEmpleados = totalEmpleados;
    }

    // Getters y Setters
    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidadEstablecimientos() {
        return cantidadEstablecimientos;
    }

    public void setCantidadEstablecimientos(int cantidadEstablecimientos) {
        this.cantidadEstablecimientos = cantidadEstablecimientos;
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(double ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    public int getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(int totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }
}
