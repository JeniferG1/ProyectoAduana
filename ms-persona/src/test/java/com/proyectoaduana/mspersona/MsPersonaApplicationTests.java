package com.proyectoaduana.mspersona;

import lombok.extern.slf4j.Slf4j;
import com.proyectoaduana.mspersona.Model.Persona;
import com.proyectoaduana.mspersona.Service.PersonaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class MsPersonaApplicationTests {

    @Autowired
    private PersonaService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando rut pasaporte correcto y no nulo")
    void checkPersona() {
        Persona persona = servicio.buscarPorId(1);
        log.info("Revisando rut pasaporte de la persona: {}", persona.getRutPasaporte());
        assertEquals("12345678-9", persona.getRutPasaporte());
        assertNotNull(persona.getRutPasaporte());
    }

    @Test
    @DisplayName("Fallo prueba id persona")
    void checkIdPersonaAsociado() {
        Persona persona = servicio.buscarPorId(1);
        log.info("Revisando nombres de la persona: {}", persona.getNombres());
        assertEquals(1, persona.getIdPersona());
    }

    @Test
    @DisplayName("Revision largo de los nombres")
    void checkLengthNombres() {
        Persona persona = servicio.buscarPorId(1);
        log.info("Revisando largo de nombres de la persona ID: {}", persona.getIdPersona());
        assertEquals(15, persona.getNombres().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de personas")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarPersonas().size();
        log.info("Verificacion de registros en la tabla Persona");
        assertEquals(2, cantidad);
    }
}
