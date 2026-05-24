package com.proyectoaduana.mspais.Controller;

import jakarta.validation.Valid;
import com.proyectoaduana.mspais.Model.Pais;
import com.proyectoaduana.mspais.Service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping("")
    public ResponseEntity<List<Pais>> getAllPaises() {
        List<Pais> listado = paisService.listarPaises();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable Integer id) {
        Pais buscado = paisService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombrePais}")
    public ResponseEntity<Pais> getPaisByNombre(@PathVariable String nombrePais) {
        Pais buscado = paisService.buscarPorNombre(nombrePais);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Pais> createPais(@RequestBody @Valid Pais pais) {
        Pais nuevo = paisService.agregarPais(pais);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Integer id) {
        boolean res = paisService.eliminarPais(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> updatePais(@PathVariable Integer id, @RequestBody @Valid Pais nuevo) {
        Pais actualizado = paisService.actualizarPais(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}