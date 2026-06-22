package com.proyectoaduana.msusuariosistema;

import com.proyectoaduana.msusuariosistema.Model.UsuarioSistema;
import com.proyectoaduana.msusuariosistema.Service.UsuarioSistemaService;
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
class MsUsuariosistemaApplicationTests {

    @Autowired
    private UsuarioSistemaService servicio;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Revisando password hash correcto y no nulo")
    void checkUsuarioSistema() {
        UsuarioSistema usuario = servicio.buscarPorId(1);
        log.info("Revisando password hash del usuario: {}", usuario.getNombreUsuario());
        assertEquals("$2a$10$e0MYzXy...", usuario.getPasswordHash());
        assertNotNull(usuario.getPasswordHash());
    }

    @Test
    @DisplayName("Fallo prueba id usuario")
    void checkIdUserAsociado() {
        UsuarioSistema usuario = servicio.buscarPorId(1);
        log.info("Revisando nombre del usuario de sistema: {}", usuario.getNombreUsuario());
        assertEquals(1, usuario.getIdUser());
    }

    @Test
    @DisplayName("Revision largo del password hash")
    void checkLengthPasswordHash() {
        UsuarioSistema usuario = servicio.buscarPorId(1);
        log.info("Revisando largo de contraseña del usuario ID: {}", usuario.getIdUser());
        assertEquals(60, usuario.getPasswordHash().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros de usuarios")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarUsuarios().size();
        log.info("Verificacion de registros en la tabla Usuario Sistema");
        assertEquals(2, cantidad);
    }
}
