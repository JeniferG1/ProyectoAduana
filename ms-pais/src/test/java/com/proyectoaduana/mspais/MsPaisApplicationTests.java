package com.proyectoaduana.mspais;


import lombok.extern.slf4j.Slf4j;
import com.proyectoaduana.mspais.Model.Pais;
import com.proyectoaduana.mspais.Service.PaisService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
@SpringBootTest
class MsPaisApplicationTests {

    @Autowired
    private PaisService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando nombre de país correcto y no nulo")
    void checkPais() {
        Pais pais = servicio.buscarPorId(1);
        log.info("Revisando país encontrado: {}", pais.getNombrePais());
        assertEquals("Uruguay", pais.getNombrePais());
        assertNotNull(pais.getNombrePais());
    }

    @Test
    @DisplayName("Fallo prueba código de país")
    void checkCodigoPais() {
        Pais pais = servicio.buscarPorId(1);
        log.info("Revisando código identificador del país: {}", pais.getNombrePais());
        assertEquals(1, pais.getCodPais());
    }

    @Test
    @DisplayName("Revision largo del nombre del país")
    void checkLengthNombrePais() {
        Pais pais = servicio.buscarPorId(1);
        log.info("Revisando cantidad de caracteres del nombre de: {}", pais.getNombrePais());
        assertEquals(5, pais.getNombrePais().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de países")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarPaises().size();
        log.info("Verificacion de registros en la tabla Pais");
        assertEquals(2, cantidad);
    }
}
