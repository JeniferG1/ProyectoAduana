package com.proyectoaduana.msdetalleproducto.Controller;

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
public class DetalleProductoController {

    @Autowired
    private DetalleProductoService detalleProductoService;

    @GetMapping("")
    public ResponseEntity<List<DetalleProducto>> getAllDetalles() {
        List<DetalleProducto> listado = detalleProductoService.listarDetalles();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleProducto> getDetalleById(@PathVariable Integer id) {
        DetalleProducto buscado = detalleProductoService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/declaracion/{idDeclaracion}")
    public ResponseEntity<List<DetalleProducto>> getDetalleByDeclaracion(@PathVariable Integer idDeclaracion) {
        List<DetalleProducto> listado = detalleProductoService.buscarPorDeclaracion(idDeclaracion);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<DetalleProducto>> getDetalleByCategoria(@PathVariable String categoria) {
        List<DetalleProducto> listado = detalleProductoService.buscarPorCategoria(categoria);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DetalleProducto> createDetalle(@RequestBody @Valid DetalleProducto detalle) {
        DetalleProducto nuevo = detalleProductoService.agregarDetalle(detalle);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Integer id) {
        boolean res = detalleProductoService.eliminarDetalle(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleProducto> updateDetalle(@PathVariable Integer id, @RequestBody @Valid DetalleProducto nuevo) {
        DetalleProducto actualizado = detalleProductoService.actualizarDetalle(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
