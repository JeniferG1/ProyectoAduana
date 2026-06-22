package com.proyectoaduana.msdetalleproducto;


import lombok.extern.slf4j.Slf4j;
import com.proyectoaduana.msdetalleproducto.Model.DetalleProducto;
import com.proyectoaduana.msdetalleproducto.Service.DetalleProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class MsDetalleproductoApplicationTests {

    @Autowired
    private DetalleProductoService servicio;

    @Test
    void contextLoads() {
    }
    @Test
    @DisplayName("Revisando categoria correcta y descripcion no nula")
    void checkDetalleProducto() {
        DetalleProducto producto = servicio.buscarPorId(1);
        log.info("Revisando producto de la categoria: {}", producto.getCategoria());
        assertEquals("Lacteos", producto.getCategoria());
        assertNotNull(producto.getDescripcion());
    }

    @Test
    @DisplayName("Fallo prueba ID Declaracion obligatoria")
    void checkProductoDeclaracion() {
        DetalleProducto producto = servicio.buscarPorId(1);
        log.info("Revisando ID de Declaracion asignado al producto: {}", producto.getIdDeclaracion());
        assertEquals(10, producto.getIdDeclaracion());
    }

    @Test
    @DisplayName("Revision largo de la descripcion")
    void checkLengthDescripcionProducto() {
        DetalleProducto producto = servicio.buscarPorId(1);
        log.info("Revisando largo del texto descriptivo del producto ID: {}", producto.getIditem());
        assertEquals(15, producto.getDescripcion().length());
    }

    @Test
    @DisplayName("Verificacion de cantidad de registros")
    void checkCantidadRegistros() {
        int cantidad = servicio.listarDetalles().size();
        log.info("Verificacion de registros de Detalle Producto");
        assertEquals(2, cantidad);
    }
}

