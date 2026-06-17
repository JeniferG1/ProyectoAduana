package com.proyectoaduana.msrelacionfamiliar.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="API Relacion familiar",description = "API para la gestion de la relacion familiar")
public class RelacionFamiliarController {

    @Autowired
    private RelacionFamiliarService relacionFamiliarService;

    @GetMapping("")
    @Operation(summary = "Obtener todas las relacion familiar", description = "Permite consultar todas las relacion familiar")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de las relacion familiar")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<RelacionFamiliar>> getAllRelaciones() {
        List<RelacionFamiliar> listado = relacionFamiliarService.listarRelaciones();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Relacion familiar segun su ID")
    public ResponseEntity<RelacionFamiliar> getRelacionById(@PathVariable Integer id) {
        RelacionFamiliar buscada = relacionFamiliarService.buscarPorId(id);
        if (buscada != null) {
            return new ResponseEntity<>(buscada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/adulto/{rutAdulto}")
    @Operation(summary = "Obtener Relacion faminiliar segun el rut del adulto")
    public ResponseEntity<List<RelacionFamiliar>> getRelacionByRutAdulto(@PathVariable String rutAdulto) {
        List<RelacionFamiliar> listado = relacionFamiliarService.buscarPorRutAdulto(rutAdulto);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/menor/{rutMenor}")
    @Operation(summary = "Obtener Relacion faminiliar segun el rut del menor")
    public ResponseEntity<List<RelacionFamiliar>> getRelacionByRutMenor(@PathVariable String rutMenor) {
        List<RelacionFamiliar> listado = relacionFamiliarService.buscarPorRutMenor(rutMenor);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar relacion familiar")
    @ApiResponse(responseCode = "201",description = "relacion familiar agregado exitosamente ")
    public ResponseEntity<RelacionFamiliar> createRelacion(@RequestBody @Valid RelacionFamiliar relacion) {
        RelacionFamiliar nueva = relacionFamiliarService.agregarRelacion(relacion);
        if (nueva != null) {
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar relacion familiar segun su ID")
    @ApiResponse(responseCode = "201",description = "relacion familiar eliminado exitosamente ")
    public ResponseEntity<Void> deleteRelacion(@PathVariable Integer id) {
        boolean res = relacionFamiliarService.eliminarRelacion(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una relacion familiar", description = "Permite modificar los datos de una relacion familiar existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "relacion familiar actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró la relacion familiar con el ID proporcionado")
    public ResponseEntity<RelacionFamiliar> updateRelacion(@PathVariable Integer id, @RequestBody @Valid RelacionFamiliar nueva) {
        RelacionFamiliar actualizada = relacionFamiliarService.actualizarRelacion(id, nueva);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
