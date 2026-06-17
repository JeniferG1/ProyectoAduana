package com.proyectoaduana.mspersona.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.proyectoaduana.mspersona.Model.Persona;
import com.proyectoaduana.mspersona.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personas")
@Tag(name="API Persona",description = "API para la gestion de las Personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("")
    @Operation(summary = "Obtener todas las Persona", description = "Permite consultar todas las Persona")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de las Personas")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> listado = personaService.listarPersonas();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Persona segun su ID")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Integer id) {
        Persona buscada = personaService.buscarPorId(id);
        if (buscada != null) {
            return new ResponseEntity<>(buscada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rut/{rutPasaporte}")
    @Operation(summary = "Obtener persona por su rut/pasaporte")
    public ResponseEntity<Persona> getPersonaByRut(@PathVariable String rutPasaporte) {
        Persona buscada = personaService.buscarPorRutPasaporte(rutPasaporte);
        if (buscada != null) {
            return new ResponseEntity<>(buscada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar persona")
    @ApiResponse(responseCode = "201",description = "Persona agregada exitosamente ")
    public ResponseEntity<Persona> createPersona(@RequestBody @Valid Persona persona) {
        Persona nueva = personaService.agregarPersona(persona);
        if (nueva != null) {
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar persona segun su ID")
    @ApiResponse(responseCode = "201",description = "persona eliminado exitosamente ")
    public ResponseEntity<Void> deletePersona(@PathVariable Integer id) {
        boolean res = personaService.eliminarPersona(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar persona ", description = "Permite modificar los datos de persona  mediante su ID")
    @ApiResponse(responseCode = "200", description = "Persona actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró persona con el ID proporcionado")
    public ResponseEntity<Persona> updatePersona(@PathVariable Integer id, @RequestBody @Valid Persona nueva) {
        Persona actualizada = personaService.actualizarPersona(id, nueva);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
