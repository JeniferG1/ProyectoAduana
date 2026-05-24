package com.proyectoaduana.mspermisocirculacion.Controller;

import jakarta.validation.Valid;
import com.proyectoaduana.mspermisocirculacion.Model.PermisoCirculacion;
import com.proyectoaduana.mspermisocirculacion.Service.PermisoCirculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permisos")
public class PermisoCirculacionController {

    @Autowired
    private PermisoCirculacionService permisoCirculacionService;

    @GetMapping("")
    public ResponseEntity<List<PermisoCirculacion>> getAllPermisos() {
        List<PermisoCirculacion> listado = permisoCirculacionService.listarPermisos();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoCirculacion> getPermisoById(@PathVariable Integer id) {
        PermisoCirculacion buscado = permisoCirculacionService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<List<PermisoCirculacion>> getPermisosByPatente(@PathVariable String patente) {
        List<PermisoCirculacion> listado = permisoCirculacionService.buscarPorPatente(patente);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/tramite/{idTramite}")
    public ResponseEntity<List<PermisoCirculacion>> getPermisosByIdTramite(@PathVariable Integer idTramite) {
        List<PermisoCirculacion> listado = permisoCirculacionService.buscarPorIdTramite(idTramite);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<PermisoCirculacion> createPermiso(@RequestBody @Valid PermisoCirculacion permiso) {
        PermisoCirculacion nuevo = permisoCirculacionService.agregarPermiso(permiso);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermiso(@PathVariable Integer id) {
        boolean res = permisoCirculacionService.eliminarPermiso(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermisoCirculacion> updatePermiso(@PathVariable Integer id, @RequestBody @Valid PermisoCirculacion nuevo) {
        PermisoCirculacion actualizado = permisoCirculacionService.actualizarPermiso(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
