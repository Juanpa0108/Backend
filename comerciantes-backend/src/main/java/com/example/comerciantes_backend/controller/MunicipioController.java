package com.example.comerciantes_backend.controller;

import com.example.comerciantes_backend.service.MunicipioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipios")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
public class MunicipioController {

    private final MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public ResponseEntity<List<String>> obtenerMunicipios() {
        List<String> municipios = municipioService.obtenerMunicipios();
        return ResponseEntity.ok(municipios);
    }
}
