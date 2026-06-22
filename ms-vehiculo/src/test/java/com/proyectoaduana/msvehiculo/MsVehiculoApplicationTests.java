package com.proyectoaduana.msvehiculo;

import com.proyectoaduana.msvehiculo.Model.Vehiculo;
import com.proyectoaduana.msvehiculo.Service.VehiculoService;
import org.junit.jupiter.api.DisplayName;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class MsVehiculoApplicationTests {

    @Autowired
    private VehiculoService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando marca correcta y patente no nula")
    void checkVehiculo() {
        Vehiculo vehiculo = servicio.buscarPorPatente("AA-BB-11");
        log.info("Revisando marca del vehiculo: {}", vehiculo.getMarca());
        assertEquals("Toyota", vehiculo.getMarca());
        assertNotNull(vehiculo.getPatente());
    }

    @Test
    @DisplayName("Fallo prueba rut dueno")
    void checkRutDuenoAsociado() {
        Vehiculo vehiculo = servicio.buscarPorPatente("AA-BB-11");
        log.info("Revisando modelo del vehiculo: {}", vehiculo.getModelo());
        assertEquals("12345678-9", vehiculo.getRutDueno());
    }

    @Test
    @DisplayName("Revision largo de la patente")
    void checkLengthPatente() {
        Vehiculo vehiculo = servicio.buscarPorPatente("AA-BB-11");
        log.info("Revisando largo de patente del vehiculo: {}", vehiculo.getPatente());
        assertEquals(8, vehiculo.getPatente().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de vehiculos")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarVehiculos().size();
        log.info("Verificacion de registros en la tabla Vehiculo");
        assertEquals(2, cantidad);
    }
}
