package com.example.comerciantes_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.comerciantes_backend.service.ComercianteService;

@RestController
@RequestMapping("/admin")
public class AdminController {

   @Autowired
    private ComercianteService comercianteService;

    @DeleteMapping("/comerciante/{id}")
    public ResponseEntity<Void> eliminarComerciante(@PathVariable Long id) {
        comercianteService.eliminarComerciante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos")
        public ResponseEntity<Resource> descargarComerciantesActivos() {
        return comercianteService.generarReporteComerciantesActivos();
    }


}
