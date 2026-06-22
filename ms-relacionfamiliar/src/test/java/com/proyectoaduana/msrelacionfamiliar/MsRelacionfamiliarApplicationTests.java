package com.proyectoaduana.msrelacionfamiliar;

import com.proyectoaduana.msrelacionfamiliar.Model.RelacionFamiliar;
import com.proyectoaduana.msrelacionfamiliar.Service.RelacionFamiliarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class MsRelacionfamiliarApplicationTests {

    @Autowired
    private RelacionFamiliarService servicio;

    @Test
    void contextLoads() {
    }


    @Test
    @DisplayName("Revisando rut adulto correcto y no nulo")
    void checkRelacionFamiliar() {
        RelacionFamiliar relacion = servicio.buscarPorId(1);
        log.info("Revisando rut del adulto en la relacion: {}", relacion.getRutAdulto());
        assertEquals("12345678-9", relacion.getRutAdulto());
        assertNotNull(relacion.getRutAdulto());
    }

    @Test
    @DisplayName("Fallo prueba id relacion")
    void checkIdRelacionAsociado() {
        RelacionFamiliar relacion = servicio.buscarPorId(1);
        log.info("Revisando parentesco de la relacion: {}", relacion.getParentesco());
        assertEquals(1, relacion.getIdRelacion());
    }

    @Test
    @DisplayName("Revision largo del rut menor")
    void checkLengthRutMenor() {
        RelacionFamiliar relacion = servicio.buscarPorId(1);
        log.info("Revisando largo del rut del menor en la relacion ID: {}", relacion.getIdRelacion());
        assertEquals(10, relacion.getRutMenor().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de relaciones familiares")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarRelaciones().size();
        log.info("Verificacion de registros en la tabla Relacion Familiar");
        assertEquals(2, cantidad);
    }
}
