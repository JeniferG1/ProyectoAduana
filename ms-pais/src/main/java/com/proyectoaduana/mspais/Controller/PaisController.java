package com.proyectoaduana.mspais.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="API Pais",description = "API para la gestion de los Pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los paises", description = "Permite consultar todos los paises")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de los paises")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<Pais>> getAllPaises() {
        List<Pais> listado = paisService.listarPaises();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pais segun su ID")
    public ResponseEntity<Pais> getPaisById(@PathVariable Integer id) {
        Pais buscado = paisService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombrePais}")
    @Operation(summary = "Obtener pais segun su nombre")
    public ResponseEntity<Pais> getPaisByNombre(@PathVariable String nombrePais) {
        Pais buscado = paisService.buscarPorNombre(nombrePais);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar los paises")
    @ApiResponse(responseCode = "201",description = "Pais agregada exitosamente ")
    public ResponseEntity<Pais> createPais(@RequestBody @Valid Pais pais) {
        Pais nuevo = paisService.agregarPais(pais);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar paises segun su ID")
    @ApiResponse(responseCode = "201",description = "Pais eliminado exitosamente ")
    public ResponseEntity<Void> deletePais(@PathVariable Integer id) {
        boolean res = paisService.eliminarPais(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pais", description = "Permite modificar los datos de un pais existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "pais actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el pais con el ID proporcionado")
    public ResponseEntity<Pais> updatePais(@PathVariable Integer id, @RequestBody @Valid Pais nuevo) {
        Pais actualizado = paisService.actualizarPais(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}