package com.proyectoaduana.mspermisocirculacion.Service;

import com.proyectoaduana.mspermisocirculacion.Model.PermisoCirculacion;
import com.proyectoaduana.mspermisocirculacion.Repository.PermisoCirculacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PermisoCirculacionService {

    @Autowired
    private PermisoCirculacionRepository permisoCirculacionRepository;
    private static final Logger log = LoggerFactory.getLogger(PermisoCirculacionService.class);

    public List<PermisoCirculacion> listarPermisos() {
        log.info("Listando todos los permisos de circulacion ");
        return permisoCirculacionRepository.findAll();
    }

    public PermisoCirculacion buscarPorId(Integer id) {
        return permisoCirculacionRepository.findById(id).orElse(null);
    }

    public List<PermisoCirculacion> buscarPorPatente(String patente) {
        return permisoCirculacionRepository.findByPatente(patente);
    }

    public List<PermisoCirculacion> buscarPorIdTramite(Integer idTramite) {
        return permisoCirculacionRepository.findByIdTramite(idTramite);
    }

    public PermisoCirculacion agregarPermiso(PermisoCirculacion nuevo) {
        try {
        log.info("Creando Permiso circulacion: {}", nuevo.getFechaEmision());
        return permisoCirculacionRepository.save(nuevo);
    }catch (Exception e) {
            log.error("Error al crear paso fronterizo: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarPermiso(Integer id) {
        try {
        if (permisoCirculacionRepository.existsById(id)) {
            log.info("Eliminando permiso de circulacion : {}", id);
            permisoCirculacionRepository.deleteById(id);
            return true;
        } else {
            log.warn("Permiso de circulacion {} no encontrado", id);
            return false;
        }
    } catch (Exception e) {
            log.error("Error al eliminar permiso de circulacion con id {}: {}", id, e.getMessage());
            return false;
        }
    }

    public PermisoCirculacion actualizarPermiso(Integer id, PermisoCirculacion nuevo) {
        try {
        if (permisoCirculacionRepository.existsById(id)) {
            PermisoCirculacion permiso = permisoCirculacionRepository.findById(id).get();
            permiso.setFechaEmision(nuevo.getFechaEmision());
            permiso.setDiasVigencia(nuevo.getDiasVigencia());
            permiso.setIdTramite(nuevo.getIdTramite());
            permiso.setPatente(nuevo.getPatente());
            permisoCirculacionRepository.save(permiso);
            return permiso;
        } else {
            return null;
        }
    } catch (Exception e) {
        log.error("Error al actualizar permiso de circulacion con id {}: {}", id, e.getMessage());
        return null;
    }
}
}

