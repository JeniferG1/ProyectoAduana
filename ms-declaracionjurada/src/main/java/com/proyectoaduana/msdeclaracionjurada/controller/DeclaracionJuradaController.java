package com.proyectoaduana.msdeclaracionjurada.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="API Declaracion Jurada",description = "API para la gestion de las Declaraciones Juradas")
public class DeclaracionJuradaController {

    @Autowired
    private DeclaracionJuradaService declaracionJuradaService;

    @GetMapping("")
    @Operation(summary = "Obtener todas las declaraciones juradas", description = "Permite consultar todas las declaraciones juradas")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de las declaraciones juradas")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<DeclaracionJurada>> getAllDeclaraciones() {
        List<DeclaracionJurada> listado = declaracionJuradaService.listarDeclaraciones();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener declaracion jurada segun su ID")
    public ResponseEntity<DeclaracionJurada> getDeclaracionById(@Parameter(description = "ID de la declaracion jurada a consultar") @PathVariable Integer id) {
        DeclaracionJurada buscada = declaracionJuradaService.buscarPorId(id);
        if (buscada != null) {
            return new ResponseEntity<>(buscada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tramite/{idTramite}")
    @Operation(summary = "Obtener declaracion jurada segun su ID Tramite ")
    public ResponseEntity<List<DeclaracionJurada>> getDeclaracionByTramite(@PathVariable Integer idTramite) {
        List<DeclaracionJurada> listado = declaracionJuradaService.buscarPorIdTramite(idTramite);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar las declaraciones juradas")
    @ApiResponse(responseCode = "201",description = "Declaracion Jurada agregada exitosamente ")
    public ResponseEntity<DeclaracionJurada> createDeclaracion(@RequestBody @Valid DeclaracionJurada declaracion) {
        DeclaracionJurada nueva = declaracionJuradaService.agregarDeclaracion(declaracion);
        if (nueva != null) {
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar las declaraciones juradas segun su ID")
    @ApiResponse(responseCode = "201",description = "Declaracion Jurada eliminada exitosamente ")
    public ResponseEntity<Void> deleteDeclaracion(@PathVariable Integer id) {
        boolean res = declaracionJuradaService.eliminarDeclaracion(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una declaracion jurada", description = "Permite modificar los datos de una declaracion existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "declaracion actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró la declaracion con el ID proporcionado")
    public ResponseEntity<DeclaracionJurada> updateDeclaracion(@PathVariable Integer id, @RequestBody @Valid DeclaracionJurada nueva) {
        DeclaracionJurada actualizada = declaracionJuradaService.actualizarDeclaracion(id, nueva);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}