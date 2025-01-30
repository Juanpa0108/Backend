package com.example.comerciantes_backend.projection;

public interface ComercianteActivoProjection {
    String getNombreRazonSocial();
    String getMunicipio();
    String getTelefono();
    String getCorreoElectronico();
    String getFechaRegistro();
    String getEstado();
    int getCantidadEstablecimientos();
    double getIngresosTotales();
    int getTotalEmpleados();
}
