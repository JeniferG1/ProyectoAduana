package com.proyectoaduana.msvehiculo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.proyectoaduana.msvehiculo.Model.Vehiculo;
import com.proyectoaduana.msvehiculo.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
@Tag(name="API vehiculo",description = "API para la gestion de los vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los vehiculos ", description = "Permite consultar todos los vehiculos")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de los vehiculos")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> listado = vehiculoService.listarVehiculos();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{patente}")
    @Operation(summary = "Obtener vehiculo segun su patente")
    public ResponseEntity<Vehiculo> getVehiculoByPatente(@PathVariable String patente) {
        Vehiculo buscado = vehiculoService.buscarPorPatente(patente);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dueno/{rutDueno}")
    @Operation(summary = "Obtener vehiculo segun el rut del dueño")
    public ResponseEntity<List<Vehiculo>> getVehiculosByRutDueno(@PathVariable String rutDueno) {
        List<Vehiculo> listado = vehiculoService.buscarPorRutDueno(rutDueno);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/marca/{marca}")
    @Operation(summary = "Obtener vehiculo segun su marca")
    public ResponseEntity<List<Vehiculo>> getVehiculosByMarca(@PathVariable String marca) {
        List<Vehiculo> listado = vehiculoService.buscarPorMarca(marca);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar vehiculo")
    @ApiResponse(responseCode = "201",description = "vehiculo agregado exitosamente ")
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody @Valid Vehiculo vehiculo) {
        Vehiculo nuevo = vehiculoService.agregarVehiculo(vehiculo);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{patente}")
    @Operation(summary = "Permite eliminar vehiculo segun su patente")
    @ApiResponse(responseCode = "201",description = "vehiculo eliminado exitosamente ")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable String patente) {
        boolean res = vehiculoService.eliminarVehiculo(patente);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{patente}")
    @Operation(summary = "Actualizar vehiculo", description = "Permite modificar los datos de un vehiculo existente mediante su patente")
    @ApiResponse(responseCode = "200", description = "vehiculo actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el vehiculo con la patente proporcionada")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable String patente, @RequestBody @Valid Vehiculo nuevo) {
        Vehiculo actualizado = vehiculoService.actualizarVehiculo(patente, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}