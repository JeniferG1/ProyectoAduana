package com.proyectoaduana.mspasofronterizo;


import lombok.extern.slf4j.Slf4j;
import com.proyectoaduana.mspasofronterizo.Service.PasoFronterizoService;
import com.proyectoaduana.mspasofronterizo.Model.PasoFronterizo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class MsPasofronterizoApplicationTests {

    @Autowired
    private PasoFronterizoService servicio;

    @Test
    void contextLoads() {
    }
    @Test
    @DisplayName("Revisando nombre de paso correcto y no nulo")
    void checkPasoFronterizo() {
        PasoFronterizo paso = servicio.buscarPorId(1);
        log.info("Revisando paso fronterizo: {}", paso.getNombrePaso());
        assertEquals("Los Libertadores", paso.getNombrePaso());
        assertNotNull(paso.getNombrePaso());
    }

    @Test
    @DisplayName("Fallo prueba código de país asociado")
    void checkCodigoPaisAsociado() {
        PasoFronterizo paso = servicio.buscarPorId(1);
        log.info("Revisando código de país del paso fronterizo: {}", paso.getNombrePaso());
        assertEquals(152, paso.getCodPais());
    }

    @Test
    @DisplayName("Revision largo del nombre del paso fronterizo")
    void checkLengthNombrePaso() {
        PasoFronterizo paso = servicio.buscarPorId(1);
        log.info("Revisando largo del texto del paso ID: {}", paso.getIdPaso());
        assertEquals(16, paso.getNombrePaso().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de pasos")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarPasos().size();
        log.info("Verificacion de registros en la tabla Paso Fronterizo");
        assertEquals(2, cantidad);
    }
}


