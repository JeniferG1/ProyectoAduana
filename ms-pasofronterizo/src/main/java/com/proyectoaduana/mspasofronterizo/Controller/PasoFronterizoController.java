package com.proyectoaduana.mspasofronterizo.Controller;

import jakarta.validation.Valid;
import com.proyectoaduana.mspasofronterizo.Model.PasoFronterizo;
import com.proyectoaduana.mspasofronterizo.Service.PasoFronterizoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pasos")
public class PasoFronterizoController {

    @Autowired
    private PasoFronterizoService pasoFronterizoService;

    @GetMapping("")
    public ResponseEntity<List<PasoFronterizo>> getAllPasos() {
        List<PasoFronterizo> listado = pasoFronterizoService.listarPasos();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasoFronterizo> getPasoById(@PathVariable Integer id) {
        PasoFronterizo buscado = pasoFronterizoService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pais/{codPais}")
    public ResponseEntity<List<PasoFronterizo>> getPasosByCodPais(@PathVariable Integer codPais) {
        List<PasoFronterizo> listado = pasoFronterizoService.buscarPorCodPais(codPais);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/nombre/{nombrePaso}")
    public ResponseEntity<PasoFronterizo> getPasoByNombre(@PathVariable String nombrePaso) {
        PasoFronterizo buscado = pasoFronterizoService.buscarPorNombre(nombrePaso);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<PasoFronterizo> createPaso(@RequestBody @Valid PasoFronterizo paso) {
        PasoFronterizo nuevo = pasoFronterizoService.agregarPaso(paso);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaso(@PathVariable Integer id) {
        boolean res = pasoFronterizoService.eliminarPaso(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PasoFronterizo> updatePaso(@PathVariable Integer id, @RequestBody @Valid PasoFronterizo nuevo) {
        PasoFronterizo actualizado = pasoFronterizoService.actualizarPaso(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
