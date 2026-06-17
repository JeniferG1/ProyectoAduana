package com.proyectoaduana.mspasofronterizo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="API Paso Fronterizo",description = "API para la gestion de los Paso fronterizo")
public class PasoFronterizoController {

    @Autowired
    private PasoFronterizoService pasoFronterizoService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los pasos fronterizos", description = "Permite consultar todos los pasos fronterizos")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de los pasos fronterizos")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<PasoFronterizo>> getAllPasos() {
        List<PasoFronterizo> listado = pasoFronterizoService.listarPasos();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paso fronterizo segun su ID")
    public ResponseEntity<PasoFronterizo> getPasoById(@PathVariable Integer id) {
        PasoFronterizo buscado = pasoFronterizoService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pais/{codPais}")
    @Operation(summary = "Obtener paso fronterizo por el id del pais")
    public ResponseEntity<List<PasoFronterizo>> getPasosByCodPais(@PathVariable Integer codPais) {
        List<PasoFronterizo> listado = pasoFronterizoService.buscarPorCodPais(codPais);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/nombre/{nombrePaso}")
    @Operation(summary = "Obtener paso fronterizo segun su nombre")
    public ResponseEntity<PasoFronterizo> getPasoByNombre(@PathVariable String nombrePaso) {
        PasoFronterizo buscado = pasoFronterizoService.buscarPorNombre(nombrePaso);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar paso fronterizo")
    @ApiResponse(responseCode = "201",description = "Paso fronterizo agregado exitosamente ")
    public ResponseEntity<PasoFronterizo> createPaso(@RequestBody @Valid PasoFronterizo paso) {
        PasoFronterizo nuevo = pasoFronterizoService.agregarPaso(paso);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar paso fronterizo segun su ID")
    @ApiResponse(responseCode = "201",description = "paso fronterizo eliminado exitosamente ")
    public ResponseEntity<Void> deletePaso(@PathVariable Integer id) {
        boolean res = pasoFronterizoService.eliminarPaso(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paso fronterizo", description = "Permite modificar los datos de un paso fronterizo existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "paso fronterizo actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el paso fronterizo con el ID proporcionado")
    public ResponseEntity<PasoFronterizo> updatePaso(@PathVariable Integer id, @RequestBody @Valid PasoFronterizo nuevo) {
        PasoFronterizo actualizado = pasoFronterizoService.actualizarPaso(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
