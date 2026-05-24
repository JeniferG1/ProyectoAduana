package com.proyectoaduana.msusuariosistema.Service;

import com.proyectoaduana.msusuariosistema.Model.UsuarioSistema;
import com.proyectoaduana.msusuariosistema.Repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;
    private static final Logger log = LoggerFactory.getLogger(UsuarioSistemaService.class);

    public List<UsuarioSistema> listarUsuarios() {
        log.info("Listando todos los usuarios del sistema ");
        return usuarioSistemaRepository.findAll();
    }

    public UsuarioSistema buscarPorId(Integer id) {
        return usuarioSistemaRepository.findById(id).orElse(null);
    }

    public UsuarioSistema buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioSistemaRepository.findByNombreUsuario(nombreUsuario);
    }

    public List<UsuarioSistema> buscarPorInstitucion(String institucion) {
        return usuarioSistemaRepository.findByInstitucion(institucion);
    }

    public List<UsuarioSistema> buscarPorCuentaActiva(Integer cuentaActiva) {
        return usuarioSistemaRepository.findByCuentaActiva(cuentaActiva);
    }

    public UsuarioSistema agregarUsuario(UsuarioSistema nuevo) {
        try {
        log.info("Creando usuario : {}", nuevo.getNombreUsuario());
        return usuarioSistemaRepository.save(nuevo);
    }catch (Exception e) {
            log.error("Error al crear el usuario: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarUsuario(Integer id) {
        try {
        if (usuarioSistemaRepository.existsById(id)) {
            log.info("Eliminando usuario : {}", id);
            usuarioSistemaRepository.deleteById(id);
            return true;
        } else {
            log.warn("usuario {} no encontrado", id);
            return false;
        }
    }catch (Exception e) {
            log.error("Error al eliminar el usuario {}: {}", id, e.getMessage());
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
            usuarioSistemaRepository.save(usuario);
            return usuario;
        } else {
            return null;
        }
    }catch (Exception e) {
            log.error("Error al actualizar el usuario {}: {}", id, e.getMessage());
            return null;
        }
    }
}
