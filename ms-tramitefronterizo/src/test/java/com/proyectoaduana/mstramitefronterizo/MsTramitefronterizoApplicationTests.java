package com.proyectoaduana.mstramitefronterizo;


import com.proyectoaduana.mstramitefronterizo.Model.TramiteFronterizo;
import com.proyectoaduana.mstramitefronterizo.Service.TramiteFronterizoService;
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
class MsTramitefronterizoApplicationTests {

    @Autowired
    private TramiteFronterizoService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando rut pasajero correcto y no nulo")
    void checkTramiteFronterizo() {
        TramiteFronterizo tramite = servicio.buscarPorId(1);
        log.info("Revisando rut del pasajero del tramite: {}", tramite.getRutPasajero());
        assertEquals("12345678-9", tramite.getRutPasajero());
        assertNotNull(tramite.getRutPasajero());
    }

    @Test
    @DisplayName("Fallo prueba id tramite")
    void checkIdTramiteAsociado() {
        TramiteFronterizo tramite = servicio.buscarPorId(1);
        log.info("Revisando sentido del tramite: {}", tramite.getSentido());
        assertEquals(1, tramite.getIdTramite());
    }

    @Test
    @DisplayName("Revision largo del sentido")
    void checkLengthSentido() {
        TramiteFronterizo tramite = servicio.buscarPorId(1);
        log.info("Revisando largo del sentido del tramite ID: {}", tramite.getIdTramite());
        assertEquals(7, tramite.getSentido().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de tramites fronterizos")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarTramites().size();
        log.info("Verificacion de registros en la tabla Tramite Fronterizo");
        assertEquals(2, cantidad);
    }
}
