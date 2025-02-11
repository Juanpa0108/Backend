package com.example.comerciantes_backend.service;

import com.example.comerciantes_backend.dto.ComercianteCDTO;
import com.example.comerciantes_backend.dto.ComercianteDTO;
import com.example.comerciantes_backend.entity.Comerciante;
import com.example.comerciantes_backend.repository.ComercianteRepository;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class ComercianteService {

    @Autowired
    private ComercianteRepository comercianteRepository;

    /**
     * Crea un nuevo comerciante validando que el correo no esté duplicado.
     */
    public Comerciante crearComerciante(@Valid ComercianteDTO comercianteDTO) {

        if (comercianteDTO.getCorreoElectronico() != null && comercianteRepository.existsByCorreoElectronico(comercianteDTO.getCorreoElectronico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está en uso.");
        }

        Comerciante comerciante = new Comerciante();
        comerciante.setNombreRazonSocial(comercianteDTO.getNombreRazonSocial());
        comerciante.setMunicipio(comercianteDTO.getMunicipio());
        comerciante.setTelefono(comercianteDTO.getTelefono());
        comerciante.setCorreoElectronico(comercianteDTO.getCorreoElectronico());
        comerciante.setFechaRegistro(comercianteDTO.getFechaRegistro());
        comerciante.setEstado(comercianteDTO.getEstado());

        return comercianteRepository.save(comerciante);
    }

    /**
     * Busca comerciantes con filtros dinámicos y paginación.
     */
    public Page<ComercianteCDTO> buscarComerciantes(String nombre, Date fechaRegistro, String estado, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return comercianteRepository.findByFiltersWithCount(nombre, fechaRegistro, estado, pageable);
    }
    

    /**
     * Obtiene un comerciante por ID.
     */
    public Comerciante obtenerPorId(Long id) {
        return comercianteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comerciante no encontrado"));
    }

    /**
     * Actualiza un comerciante existente validando que el correo no esté en uso por otro.
     */
    public Comerciante actualizarComerciante(Long id, @Valid ComercianteDTO comercianteDTO) {
        Comerciante comerciante = comercianteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comerciante no encontrado"));
    
        if (comercianteDTO.getCorreoElectronico() != null 
            && !comerciante.getCorreoElectronico().equals(comercianteDTO.getCorreoElectronico())
            && comercianteRepository.existsByCorreoElectronico(comercianteDTO.getCorreoElectronico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está en uso.");
        }
    
        if (comercianteDTO.getNombreRazonSocial() != null) {
            comerciante.setNombreRazonSocial(comercianteDTO.getNombreRazonSocial());
        }
        if (comercianteDTO.getMunicipio() != null) {
            comerciante.setMunicipio(comercianteDTO.getMunicipio());
        }
        if (comercianteDTO.getTelefono() != null) {
            comerciante.setTelefono(comercianteDTO.getTelefono());
        }
        if (comercianteDTO.getCorreoElectronico() != null) {
            comerciante.setCorreoElectronico(comercianteDTO.getCorreoElectronico());
        }
        if (comercianteDTO.getEstado() != null) {
            comerciante.setEstado(comercianteDTO.getEstado());
        }
        if (comercianteDTO.getFechaRegistro() != null) { // ✅ Permitir actualizar fecha de registro
            comerciante.setFechaRegistro(comercianteDTO.getFechaRegistro());
        }
    
        return comercianteRepository.save(comerciante);
    }

    public Boolean eliminarComerciante(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comerciante no encontrado"));
        
        comercianteRepository.delete(comerciante);
        return true;
    }

    public boolean actualizarEstadoComerciante(Long id, String nuevoEstado) {
        if (!nuevoEstado.equalsIgnoreCase("Activo") && !nuevoEstado.equalsIgnoreCase("Inactivo")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado no válido. Debe ser 'Activo' o 'Inactivo'.");
        }

        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comerciante no encontrado"));

        comerciante.setEstado(nuevoEstado);
        comercianteRepository.save(comerciante);
        return true;
    }

       public ResponseEntity<Resource> generarReporteComerciantesActivos() {
        List<String> datos = comercianteRepository.obtenerComerciantesActivos();

        if (datos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay comerciantes activos.");
        }

        // Convertir lista en un string separado por saltos de línea
        String contenido = String.join("\n", datos);

        // Crear recurso para descarga
        ByteArrayResource recurso = new ByteArrayResource(contenido.getBytes(StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comerciantes_activos.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(recurso);
    }
    
}