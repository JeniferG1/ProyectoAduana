package com.proyectoaduana.msusuariosistema.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.proyectoaduana.msusuariosistema.Model.UsuarioSistema;
import com.proyectoaduana.msusuariosistema.Service.UsuarioSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name="API usuario sistema",description = "API para la gestion de los usuarios del sistema")
public class UsuarioSistemaController {

    @Autowired
    private UsuarioSistemaService usuarioSistemaService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los usuarios ", description = "Permite consultar todos los usuarios")
    @ApiResponse(responseCode = "200",description = "consulta exitosa se entrega la lista de los usuarios")
    @ApiResponse(responseCode = "204",description = "consulta existosa, pero no se encontraron datos")
    public ResponseEntity<List<UsuarioSistema>> getAllUsuarios() {
        List<UsuarioSistema> listado = usuarioSistemaService.listarUsuarios();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario segun su ID")
    public ResponseEntity<UsuarioSistema> getUsuarioById(@PathVariable Integer id) {
        UsuarioSistema buscado = usuarioSistemaService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombreUsuario}")
    @Operation(summary = "Obtener usuario segun su nombre")
    public ResponseEntity<UsuarioSistema> getUsuarioByNombre(@PathVariable String nombreUsuario) {
        UsuarioSistema buscado = usuarioSistemaService.buscarPorNombreUsuario(nombreUsuario);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/institucion/{institucion}")
    @Operation(summary = "Obtener usuario segun su institucion")
    public ResponseEntity<List<UsuarioSistema>> getUsuariosByInstitucion(@PathVariable String institucion) {
        List<UsuarioSistema> listado = usuarioSistemaService.buscarPorInstitucion(institucion);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Permite agregar usuario")
    @ApiResponse(responseCode = "201",description = "usuario agregado exitosamente ")
    public ResponseEntity<UsuarioSistema> createUsuario(@RequestBody @Valid UsuarioSistema usuario) {
        UsuarioSistema nuevo = usuarioSistemaService.agregarUsuario(usuario);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar usuario segun su ID")
    @ApiResponse(responseCode = "201",description = "usuario eliminado exitosamente ")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        boolean res = usuarioSistemaService.eliminarUsuario(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Permite modificar los datos de un usuario existente mediante su ID")
    @ApiResponse(responseCode = "200", description = "usuario actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontró el usuario con el ID proporcionado")
    public ResponseEntity<UsuarioSistema> updateUsuario(@PathVariable Integer id, @RequestBody @Valid UsuarioSistema nuevo) {
        UsuarioSistema actualizado = usuarioSistemaService.actualizarUsuario(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
