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
        log.info("Creando usuario : {}", nuevo.getNombreUsuario());
        return usuarioSistemaRepository.save(nuevo);
    }

    public boolean eliminarUsuario(Integer id) {
        if (usuarioSistemaRepository.existsById(id)) {
            log.info("Eliminando usuario : {}", id);
            usuarioSistemaRepository.deleteById(id);
            return true;
        } else {
            log.warn("usuario {} no encontrado", id);
            return false;
        }
    }

    public UsuarioSistema actualizarUsuario(Integer id, UsuarioSistema nuevo) {
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
    }
}
