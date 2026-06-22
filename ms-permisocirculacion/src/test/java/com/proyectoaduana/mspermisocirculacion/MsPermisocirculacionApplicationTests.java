package com.proyectoaduana.mspermisocirculacion;

import lombok.extern.slf4j.Slf4j;
import com.proyectoaduana.mspermisocirculacion.Model.PermisoCirculacion;
import com.proyectoaduana.mspermisocirculacion.Service.PermisoCirculacionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class MsPermisocirculacionApplicationTests {

    @Autowired
    private PermisoCirculacionService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando patente correcta y no nula")
    void checkPermisoCirculacion() {
        PermisoCirculacion permiso = servicio.buscarPorId(1);
        log.info("Revisando patente del permiso: {}", permiso.getPatente());
        assertEquals("Rx80TZ", permiso.getPatente());
        assertNotNull(permiso.getPatente());
    }

    @Test
    @DisplayName("Fallo prueba id tramite asociado")
    void checkIdTramiteAsociado() {
        PermisoCirculacion permiso = servicio.buscarPorId(1);
        log.info("Revisando id de tramite del permiso: {}", permiso.getPatente());
        assertEquals(500, permiso.getIdTramite());
    }

    @Test
    @DisplayName("Revision largo de la patente")
    void checkLengthPatente() {
        PermisoCirculacion permiso = servicio.buscarPorId(1);
        log.info("Revisando largo de patente del permiso ID: {}", permiso.getIdPermiso());
        assertEquals(8, permiso.getPatente().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de permisos")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarPermisos().size();
        log.info("Verificacion de registros en la tabla Permiso Circulacion");
        assertEquals(2, cantidad);
    }
}
