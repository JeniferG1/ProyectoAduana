package com.proyectoaduana.msvehiculo.Controller;

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
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> listado = vehiculoService.listarVehiculos();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{patente}")
    public ResponseEntity<Vehiculo> getVehiculoByPatente(@PathVariable String patente) {
        Vehiculo buscado = vehiculoService.buscarPorPatente(patente);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dueno/{rutDueno}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByRutDueno(@PathVariable String rutDueno) {
        List<Vehiculo> listado = vehiculoService.buscarPorRutDueno(rutDueno);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByMarca(@PathVariable String marca) {
        List<Vehiculo> listado = vehiculoService.buscarPorMarca(marca);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody @Valid Vehiculo vehiculo) {
        Vehiculo nuevo = vehiculoService.agregarVehiculo(vehiculo);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{patente}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable String patente) {
        boolean res = vehiculoService.eliminarVehiculo(patente);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{patente}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable String patente, @RequestBody @Valid Vehiculo nuevo) {
        Vehiculo actualizado = vehiculoService.actualizarVehiculo(patente, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}