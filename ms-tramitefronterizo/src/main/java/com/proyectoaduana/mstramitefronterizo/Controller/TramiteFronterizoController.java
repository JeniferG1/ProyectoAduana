package com.proyectoaduana.mstramitefronterizo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="API tramite fronterizo",description = "API para la gestion de los tramites fronterizos")
public class TramiteFronterizoController {

    @Autowired
    private TramiteFronterizoService tramiteFronterizoService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los tramites fronterizos", description = "Permite consultar todos los tramites fronterizos")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de los tramites fronterizo")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<TramiteFronterizo>> getAllTramites() {
        List<TramiteFronterizo> listado = tramiteFronterizoService.listarTramites();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tramite fronterizo segun su ID")
    public ResponseEntity<TramiteFronterizo> getTramiteById(@PathVariable Integer id) {
        TramiteFronterizo buscado = tramiteFronterizoService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pasajero/{rutPasajero}")
    @Operation(summary = "Obtener tramite fronterizo segun el rut del pasajero")
    public ResponseEntity<List<TramiteFronterizo>> getTramitesByRutPasajero(@PathVariable String rutPasajero) {
        List<TramiteFronterizo> listado = tramiteFronterizoService.buscarPorRutPasajero(rutPasajero);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/paso/{idPaso}")
    @Operation(summary = "Obtener tramite fronterizo segun el id del paso ")
    public ResponseEntity<List<TramiteFronterizo>> getTramitesByIdPaso(@PathVariable Integer idPaso) {
        List<TramiteFronterizo> listado = tramiteFronterizoService.buscarPorIdPaso(idPaso);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }


    @PostMapping("/")
    @Operation(summary = "Permite agregar tramite fronterizo")
    @ApiResponse(responseCode = "201",description = "tramite fronterizo agregado exitosamente ")
    public ResponseEntity<TramiteFronterizo> createTramite(@RequestBody @Valid TramiteFronterizo tramite) {
        TramiteFronterizo nuevo = tramiteFronterizoService.agregarTramite(tramite);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar tramite fronterizo segun su ID")
    @ApiResponse(responseCode = "201",description = "tramite fronterizo eliminado exitosamente ")
    public ResponseEntity<Void> deleteTramite(@PathVariable Integer id) {
        boolean res = tramiteFronterizoService.eliminarTramite(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un tramite fronterizo", description = "Permite modificar los datos de un tramite existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "tramite actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el tramite fronterizo con el ID proporcionado")
    public ResponseEntity<TramiteFronterizo> updateTramite(@PathVariable Integer id, @RequestBody @Valid TramiteFronterizo nuevo) {
        TramiteFronterizo actualizado = tramiteFronterizoService.actualizarTramite(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/completo/{id}")
    @Operation(summary = "Obtener detalles completos de un tramite", description = "Retorna toda la información detallada y relacionada del tramite por su ID")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa, se entregan los datos completos")
    @ApiResponse(responseCode = "404", description = "No se encontró el permiso solicitado")
    public ResponseEntity<Map<String, Object>> getTramiteCompleto(@PathVariable Integer id) {
        Map<String, Object> resultado = tramiteFronterizoService.obtenerTramiteCompleto(id);
        if (resultado != null) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
