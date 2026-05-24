package com.proyectoaduana.mstramitefronterizo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-pasofronterizo")
public interface PasoFronterizoClient {

    @GetMapping("/api/v1/pasos/{id}")
    Object getPasoById(@PathVariable Integer id);
}
