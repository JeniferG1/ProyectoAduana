package com.proyectoaduana.mstramitefronterizo.Controller;

import jakarta.validation.Valid;
import com.proyectoaduana.mstramitefronterizo.Model.TramiteFronterizo;
import com.proyectoaduana.mstramitefronterizo.Service.TramiteFronterizoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tramites")
public class TramiteFronterizoController {

    @Autowired
    private TramiteFronterizoService tramiteFronterizoService;

    @GetMapping("")
    public ResponseEntity<List<TramiteFronterizo>> getAllTramites() {
        List<TramiteFronterizo> listado = tramiteFronterizoService.listarTramites();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TramiteFronterizo> getTramiteById(@PathVariable Integer id) {
        TramiteFronterizo buscado = tramiteFronterizoService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pasajero/{rutPasajero}")
    public ResponseEntity<List<TramiteFronterizo>> getTramitesByRutPasajero(@PathVariable String rutPasajero) {
        List<TramiteFronterizo> listado = tramiteFronterizoService.buscarPorRutPasajero(rutPasajero);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/paso/{idPaso}")
    public ResponseEntity<List<TramiteFronterizo>> getTramitesByIdPaso(@PathVariable Integer idPaso) {
        List<TramiteFronterizo> listado = tramiteFronterizoService.buscarPorIdPaso(idPaso);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }


    @PostMapping("/")
    public ResponseEntity<TramiteFronterizo> createTramite(@RequestBody @Valid TramiteFronterizo tramite) {
        TramiteFronterizo nuevo = tramiteFronterizoService.agregarTramite(tramite);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTramite(@PathVariable Integer id) {
        boolean res = tramiteFronterizoService.eliminarTramite(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TramiteFronterizo> updateTramite(@PathVariable Integer id, @RequestBody @Valid TramiteFronterizo nuevo) {
        TramiteFronterizo actualizado = tramiteFronterizoService.actualizarTramite(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/completo/{id}")
    public ResponseEntity<Map<String, Object>> getTramiteCompleto(@PathVariable Integer id) {
        Map<String, Object> resultado = tramiteFronterizoService.obtenerTramiteCompleto(id);
        if (resultado != null) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
