package com.proyectoaduana.mspermisocirculacion.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-vehiculo")
public interface VehiculoClient {

    @GetMapping("/api/v1/vehiculos/{patente}")
    Object getVehiculoByPatente(@PathVariable String patente);
}