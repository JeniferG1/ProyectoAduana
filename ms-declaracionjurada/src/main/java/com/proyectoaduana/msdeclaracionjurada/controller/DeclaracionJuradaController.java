package com.proyectoaduana.msdeclaracionjurada.controller;

import jakarta.validation.Valid;
import com.proyectoaduana.msdeclaracionjurada.Model.DeclaracionJurada;
import com.proyectoaduana.msdeclaracionjurada.Service.DeclaracionJuradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/declaraciones")
public class DeclaracionJuradaController {

    @Autowired
    private DeclaracionJuradaService declaracionJuradaService;

    @GetMapping("")
    public ResponseEntity<List<DeclaracionJurada>> getAllDeclaraciones() {
        List<DeclaracionJurada> listado = declaracionJuradaService.listarDeclaraciones();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclaracionJurada> getDeclaracionById(@PathVariable Integer id) {
        DeclaracionJurada buscada = declaracionJuradaService.buscarPorId(id);
        if (buscada != null) {
            return new ResponseEntity<>(buscada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tramite/{idTramite}")
    public ResponseEntity<List<DeclaracionJurada>> getDeclaracionByTramite(@PathVariable Integer idTramite) {
        List<DeclaracionJurada> listado = declaracionJuradaService.buscarPorIdTramite(idTramite);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DeclaracionJurada> createDeclaracion(@RequestBody @Valid DeclaracionJurada declaracion) {
        DeclaracionJurada nueva = declaracionJuradaService.agregarDeclaracion(declaracion);
        if (nueva != null) {
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeclaracion(@PathVariable Integer id) {
        boolean res = declaracionJuradaService.eliminarDeclaracion(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeclaracionJurada> updateDeclaracion(@PathVariable Integer id, @RequestBody @Valid DeclaracionJurada nueva) {
        DeclaracionJurada actualizada = declaracionJuradaService.actualizarDeclaracion(id, nueva);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}