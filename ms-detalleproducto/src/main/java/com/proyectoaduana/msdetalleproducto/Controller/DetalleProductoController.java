package com.proyectoaduana.msdetalleproducto.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.proyectoaduana.msdetalleproducto.Model.DetalleProducto;
import com.proyectoaduana.msdetalleproducto.Service.DetalleProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles")
@Tag(name="API detalle producto",description = "API para la gestion detalle producto")
public class DetalleProductoController {

    @Autowired
    private DetalleProductoService detalleProductoService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los detalle producto", description = "Permite consultar todos los detalle producto")
    @ApiResponse(responseCode = "200",description = "consulta exitosa, se entrega la lista de los detalle producto")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<DetalleProducto>> getAllDetalles() {
        List<DetalleProducto> listado = detalleProductoService.listarDetalles();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle producto segun su ID")
    public ResponseEntity<DetalleProducto> getDetalleById(@PathVariable Integer id) {
        DetalleProducto buscado = detalleProductoService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/declaracion/{idDeclaracion}")
    @Operation(summary = "Obtener detalle producto segun su ID declaracion ")
    public ResponseEntity<List<DetalleProducto>> getDetalleByDeclaracion(@PathVariable Integer idDeclaracion) {
        List<DetalleProducto> listado = detalleProductoService.buscarPorDeclaracion(idDeclaracion);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtener detalle producto segun su categoria ")
    public ResponseEntity<List<DetalleProducto>> getDetalleByCategoria(@PathVariable String categoria) {
        List<DetalleProducto> listado = detalleProductoService.buscarPorCategoria(categoria);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar los detalle producto")
    @ApiResponse(responseCode = "201",description = "Detalle producto agregado exitosamente ")
    public ResponseEntity<DetalleProducto> createDetalle(@RequestBody @Valid DetalleProducto detalle) {
        DetalleProducto nuevo = detalleProductoService.agregarDetalle(detalle);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar los detalle producto segun su ID")
    @ApiResponse(responseCode = "201",description = "Detalle producto eliminado exitosamente ")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Integer id) {
        boolean res = detalleProductoService.eliminarDetalle(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un detalle producto", description = "Permite modificar los datos de un detalle producto existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "detalle producto actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el detalle producto con el ID proporcionado")
    public ResponseEntity<DetalleProducto> updateDetalle(@PathVariable Integer id, @RequestBody @Valid DetalleProducto nuevo) {
        DetalleProducto actualizado = detalleProductoService.actualizarDetalle(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
