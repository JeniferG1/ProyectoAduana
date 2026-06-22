package com.proyectoaduana.msdeclaracionjurada;

import lombok.extern.slf4j.Slf4j;
import com.proyectoaduana.msdeclaracionjurada.Model.DeclaracionJurada;
import com.proyectoaduana.msdeclaracionjurada.Service.DeclaracionJuradaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@SpringBootTest
class DeclaracionJuradaApplicationTests {

    @Autowired
    private DeclaracionJuradaService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando firma digital correcta y alerta de riesgo nula")
    void checkDeclaracionJurada() {
        DeclaracionJurada declaracion = servicio.buscarPorId(1);
        log.info("Revisando declaracion {}", declaracion.getFirmaDigital());
        assertEquals("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A...", declaracion.getFirmaDigital());
        assertNull(declaracion.getAlertaRiesgo());
    }

    @Test
    @DisplayName("Fallo prueba ID Tramite")
    void checkDeclaracionTramite() {
        DeclaracionJurada declaracion = servicio.buscarPorId(1);
        log.info("Revisando id de tramite de la firma: {}", declaracion.getFirmaDigital());
        assertEquals(500, declaracion.getIdTramite());
    }

    @Test
    @DisplayName("Revision largo de la firma digital")
    void checkLengthFirmaDeclaracion() {
        DeclaracionJurada Declaracion = servicio.buscarPorId(1);
        log.info("Revisando largo de la firma de la declaracion ID: {}", Declaracion.getIdDeclaracion());
        assertEquals(40, Declaracion.getFirmaDigital().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarDeclaraciones().size();
        log.info("Verificacion de registros de Declaraciones Juradas");
        assertEquals(2, cantidad);
    }
}