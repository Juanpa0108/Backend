package com.example.comerciantes_backend.service;

import com.example.comerciantes_backend.entity.Establecimiento;
import com.example.comerciantes_backend.repository.EstablecimientoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResumenEstablecimientosService {

    private final EstablecimientoRepository establecimientoRepository;

    public ResumenEstablecimientosService(EstablecimientoRepository establecimientoRepository) {
        this.establecimientoRepository = establecimientoRepository;
    }

    public Map<String, Object> obtenerResumenEstablecimientos(Long comercianteId) {
        List<Establecimiento> establecimientos = establecimientoRepository.findByComercianteId(comercianteId);

        // Calcular sumatoria de ingresos y empleados
        double totalIngresos = establecimientos.stream()
                .mapToDouble(Establecimiento::getIngresos)
                .sum();

                int totalEmpleados = establecimientos.stream()
                .map(est -> est.getNumeroEmpleados() != null ? est.getNumeroEmpleados() : 0) // Usar getNumeroEmpleados()
                .mapToInt(Integer::intValue) // Convertir Integer a int
                .sum();
            

        // Retornar respuesta estructurada
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalIngresos", totalIngresos);
        resumen.put("totalEmpleados", totalEmpleados);

        return resumen;
    }
}
