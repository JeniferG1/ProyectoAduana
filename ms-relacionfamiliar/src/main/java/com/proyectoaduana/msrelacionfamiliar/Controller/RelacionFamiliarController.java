package com.proyectoaduana.msrelacionfamiliar.Controller;

import jakarta.validation.Valid;
import com.proyectoaduana.msrelacionfamiliar.Model.RelacionFamiliar;
import com.proyectoaduana.msrelacionfamiliar.Service.RelacionFamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/relaciones")
public class RelacionFamiliarController {

    @Autowired
    private RelacionFamiliarService relacionFamiliarService;

    @GetMapping("")
    public ResponseEntity<List<RelacionFamiliar>> getAllRelaciones() {
        List<RelacionFamiliar> listado = relacionFamiliarService.listarRelaciones();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelacionFamiliar> getRelacionById(@PathVariable Integer id) {
        RelacionFamiliar buscada = relacionFamiliarService.buscarPorId(id);
        if (buscada != null) {
            return new ResponseEntity<>(buscada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/adulto/{rutAdulto}")
    public ResponseEntity<List<RelacionFamiliar>> getRelacionByRutAdulto(@PathVariable String rutAdulto) {
        List<RelacionFamiliar> listado = relacionFamiliarService.buscarPorRutAdulto(rutAdulto);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/menor/{rutMenor}")
    public ResponseEntity<List<RelacionFamiliar>> getRelacionByRutMenor(@PathVariable String rutMenor) {
        List<RelacionFamiliar> listado = relacionFamiliarService.buscarPorRutMenor(rutMenor);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<RelacionFamiliar> createRelacion(@RequestBody @Valid RelacionFamiliar relacion) {
        RelacionFamiliar nueva = relacionFamiliarService.agregarRelacion(relacion);
        if (nueva != null) {
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelacion(@PathVariable Integer id) {
        boolean res = relacionFamiliarService.eliminarRelacion(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelacionFamiliar> updateRelacion(@PathVariable Integer id, @RequestBody @Valid RelacionFamiliar nueva) {
        RelacionFamiliar actualizada = relacionFamiliarService.actualizarRelacion(id, nueva);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
