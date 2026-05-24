package com.proyectoaduana.msusuariosistema.Controller;

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
public class UsuarioSistemaController {

    @Autowired
    private UsuarioSistemaService usuarioSistemaService;

    @GetMapping("")
    public ResponseEntity<List<UsuarioSistema>> getAllUsuarios() {
        List<UsuarioSistema> listado = usuarioSistemaService.listarUsuarios();
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSistema> getUsuarioById(@PathVariable Integer id) {
        UsuarioSistema buscado = usuarioSistemaService.buscarPorId(id);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombreUsuario}")
    public ResponseEntity<UsuarioSistema> getUsuarioByNombre(@PathVariable String nombreUsuario) {
        UsuarioSistema buscado = usuarioSistemaService.buscarPorNombreUsuario(nombreUsuario);
        if (buscado != null) {
            return new ResponseEntity<>(buscado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/institucion/{institucion}")
    public ResponseEntity<List<UsuarioSistema>> getUsuariosByInstitucion(@PathVariable String institucion) {
        List<UsuarioSistema> listado = usuarioSistemaService.buscarPorInstitucion(institucion);
        if (listado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listado, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioSistema> createUsuario(@RequestBody @Valid UsuarioSistema usuario) {
        UsuarioSistema nuevo = usuarioSistemaService.agregarUsuario(usuario);
        if (nuevo != null) {
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        boolean res = usuarioSistemaService.eliminarUsuario(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioSistema> updateUsuario(@PathVariable Integer id, @RequestBody @Valid UsuarioSistema nuevo) {
        UsuarioSistema actualizado = usuarioSistemaService.actualizarUsuario(id, nuevo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
