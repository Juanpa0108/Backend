package com.example.comerciantes_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MunicipioService {

    private final String API_URL = "https://www.datos.gov.co/resource/xdk5-pm3f.json";

    public List<String> obtenerMunicipios() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            @SuppressWarnings("deprecation")
            String url = UriComponentsBuilder.fromHttpUrl(API_URL).toUriString();

            // Llamamos a la API y obtenemos una lista de mapas
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
            List<String> municipios = new ArrayList<>();

            if (response != null) {
                // Extraemos solo los nombres de los municipios
                for (Map<String, Object> item : response) {
                    if (item.containsKey("municipio")) {
                        municipios.add(item.get("municipio").toString());
                    }
                }
            }

            return municipios;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener municipios: " + e.getMessage());
        }
    }
}
