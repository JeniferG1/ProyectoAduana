package com.proyectoaduana.mspermisocirculacion.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.proyectoaduana.mspermisocirculacion.Model.PermisoCirculacion;
import com.proyectoaduana.mspermisocirculacion.Service.PermisoCirculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/permisos")
@Tag(name="API Permiso circulacion",description = "API para la gestion de los Permiso de circulacion")
public class PermisoCirculacionController {

    @Autowired
    private PermisoCirculacionService permisoCirculacionService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los Permiso de circulacion", description = "Permite consultar todos los Permiso de circulacion")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de los Permiso de circulacion")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<PermisoCirculacion>> getAllPermisos() {
        List<PermisoCirculacion> listado = permisoCirculacionService.listarPermisos();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Permiso de circulacion segun su ID")
    public ResponseEntity<PermisoCirculacion> getPermisoById(@PathVariable Integer id) {
        PermisoCirculacion buscado = permisoCirculacionService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patente/{patente}")
    @Operation(summary = "Obtener permiso de circulacion por la patente")
    public ResponseEntity<List<PermisoCirculacion>> getPermisosByPatente(@PathVariable String patente) {
        List<PermisoCirculacion> listado = permisoCirculacionService.buscarPorPatente(patente);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/tramite/{idTramite}")
    @Operation(summary = "Obtener permiso de circulacion por el id del tramite")
    public ResponseEntity<List<PermisoCirculacion>> getPermisosByIdTramite(@PathVariable Integer idTramite) {
        List<PermisoCirculacion> listado = permisoCirculacionService.buscarPorIdTramite(idTramite);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar permiso de circulacion")
    @ApiResponse(responseCode = "201",description = "Permiso de circulacion agregado exitosamente ")
    public ResponseEntity<PermisoCirculacion> createPermiso(@RequestBody @Valid PermisoCirculacion permiso) {
        PermisoCirculacion nuevo = permisoCirculacionService.agregarPermiso(permiso);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar permiso de circulacion segun su ID")
    @ApiResponse(responseCode = "201",description = "permiso de circulacion eliminado exitosamente ")
    public ResponseEntity<Void> deletePermiso(@PathVariable Integer id) {
        boolean res = permisoCirculacionService.eliminarPermiso(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un permiso de circulación", description = "Permite modificar los datos de un permiso existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "Permiso actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el permiso con el ID proporcionado")
    public ResponseEntity<PermisoCirculacion> updatePermiso(@PathVariable Integer id, @RequestBody @Valid PermisoCirculacion nuevo) {
        PermisoCirculacion actualizado = permisoCirculacionService.actualizarPermiso(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/completo/{id}")
    @Operation(summary = "Obtener detalles completos de un permiso", description = "Retorna toda la información detallada y relacionada del permiso por su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, se entregan los datos completos")
    @ApiResponse(responseCode = "404", description = "No se encontró el permiso solicitado")
    public ResponseEntity<Map<String, Object>> getPermisoCompleto(@PathVariable Integer id) {
        Map<String, Object> resultado = permisoCirculacionService.obtenerPermisoCompleto(id);
        if (resultado != null) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}