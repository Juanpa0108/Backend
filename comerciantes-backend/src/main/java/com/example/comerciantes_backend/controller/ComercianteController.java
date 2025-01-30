package com.example.comerciantes_backend.controller;

import com.example.comerciantes_backend.dto.ComercianteCDTO;
import com.example.comerciantes_backend.dto.ComercianteDTO;
import com.example.comerciantes_backend.entity.Comerciante;
import com.example.comerciantes_backend.service.ComercianteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/comerciantes")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;

    /**
     * Endpoint para obtener comerciantes con paginación y filtros dinámicos.
     *
     * @param nombre        (Opcional) Nombre o razón social
     * @param fechaRegistro (Opcional) Fecha de registro (Formato: yyyy-MM-dd)
     * @param estado        (Opcional) Estado del comerciante
     * @param page          Número de página (por defecto 0)
     * @param size          Cantidad de registros por página (por defecto 5)
     * @return Página con comerciantes filtrados
     */
    @GetMapping("/buscar")
    public ResponseEntity<Page<ComercianteCDTO>> buscarComerciantes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaRegistro,
            @RequestParam(required = false) String estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<ComercianteCDTO> resultado = comercianteService.buscarComerciantes(nombre, fechaRegistro, estado, page, size);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comerciante> obtenerComerciantePorId(@PathVariable Long id) {
        Comerciante comerciante = comercianteService.obtenerPorId(id);
        return ResponseEntity.ok(comerciante);
    }

    @PostMapping("/create")
     public ResponseEntity<?> crearComerciante(@Valid @RequestBody ComercianteDTO comercianteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldErrors().stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList()));
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(comercianteService.crearComerciante(comercianteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarComerciante(@PathVariable Long id, @Valid @RequestBody ComercianteDTO comercianteDTO, BindingResult result) {
    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getFieldErrors().stream()
            .map(err -> err.getDefaultMessage())
            .collect(Collectors.toList()));
    }

    Comerciante comercianteActualizado = comercianteService.actualizarComerciante(id, comercianteDTO);
    return ResponseEntity.ok(comercianteActualizado);
}

@PatchMapping("/estado/{id}")
    public ResponseEntity<Boolean> actualizarEstadoComerciante(
            @PathVariable Long id,
            @RequestParam String estado) {

        boolean actualizado = comercianteService.actualizarEstadoComerciante(id, estado);
        return ResponseEntity.ok(actualizado);
    }
}
