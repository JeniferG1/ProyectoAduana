package com.proyectoaduana.msusuariosistema.Service;

import com.proyectoaduana.msusuariosistema.Model.UsuarioSistema;
import com.proyectoaduana.msusuariosistema.Repository.UsuarioSistemaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSistemaService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioSistemaService.class);

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    public List<UsuarioSistema> listarUsuarios() {
        log.info("Listando todos los usuarios");
        return usuarioSistemaRepository.findAll();
    }

    public UsuarioSistema buscarPorId(Integer id) {
        log.info("Buscando usuario con id: {}", id);
        return usuarioSistemaRepository.findById(id).orElse(null);
    }

    public UsuarioSistema buscarPorNombreUsuario(String nombreUsuario) {
        log.info("Buscando usuario por nombre: {}", nombreUsuario);
        return usuarioSistemaRepository.findByNombreUsuario(nombreUsuario);
    }

    public List<UsuarioSistema> buscarPorInstitucion(String institucion) {
        log.info("Buscando usuarios por institucion: {}", institucion);
        return usuarioSistemaRepository.findByInstitucion(institucion);
    }

    public List<UsuarioSistema> buscarPorCuentaActiva(Integer cuentaActiva) {
        log.info("Buscando usuarios por cuenta activa: {}", cuentaActiva);
        return usuarioSistemaRepository.findByCuentaActiva(cuentaActiva);
    }

    public UsuarioSistema agregarUsuario(UsuarioSistema nuevo) {
        try {
            log.info("Creando usuario: {}", nuevo.getNombreUsuario());
            nuevo.setIdUser(null);
            return usuarioSistemaRepository.save(nuevo);
        } catch (Exception e) {
            log.error("Error al crear usuario: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarUsuario(Integer id) {
        try {
            if (usuarioSistemaRepository.existsById(id)) {
                log.info("Eliminando usuario con id: {}", id);
                usuarioSistemaRepository.deleteById(id);
                return true;
            } else {
                log.warn("Usuario con id {} no encontrado", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar usuario: {}", e.getMessage());
            return false;
        }
    }

    public UsuarioSistema actualizarUsuario(Integer id, UsuarioSistema nuevo) {
        try {
            if (usuarioSistemaRepository.existsById(id)) {
                UsuarioSistema usuario = usuarioSistemaRepository.findById(id).get();
                usuario.setNombreUsuario(nuevo.getNombreUsuario());
                usuario.setPasswordHash(nuevo.getPasswordHash());
                usuario.setInstitucion(nuevo.getInstitucion());
                usuario.setCuentaActiva(nuevo.getCuentaActiva());
                log.info("Actualizando usuario con id: {}", id);
                return usuarioSistemaRepository.save(usuario);
            } else {
                log.warn("Usuario con id {} no encontrado para actualizar", id);
                return null;
            }
        } catch (Exception e) {
            log.error("Error al actualizar usuario: {}", e.getMessage());
            return null;
        }
    }
}