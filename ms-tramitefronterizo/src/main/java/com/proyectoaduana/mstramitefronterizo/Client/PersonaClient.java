package com.proyectoaduana.mstramitefronterizo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-persona")
public interface PersonaClient {

    @GetMapping("/api/v1/personas/{id}")
    Object getPersonaById(@PathVariable Integer id);

    @GetMapping("/api/v1/personas/rut/{rutPasaporte}")
    Object getPersonaByRut(@PathVariable String rutPasaporte);
}
