package com.proyectoaduana.mspermisocirculacion.Service;

import com.proyectoaduana.mspermisocirculacion.Client.VehiculoClient;
import com.proyectoaduana.mspermisocirculacion.Model.PermisoCirculacion;
import com.proyectoaduana.mspermisocirculacion.Repository.PermisoCirculacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermisoCirculacionService {

    private static final Logger log = LoggerFactory.getLogger(PermisoCirculacionService.class);


    @Autowired
    private PermisoCirculacionRepository permisoCirculacionRepository;

    @Autowired
    private VehiculoClient vehiculoClient;



    public List<PermisoCirculacion> listarPermisos() {
        log.info("Listando todos los permisos de circulacion");
        return permisoCirculacionRepository.findAll();
    }


    public PermisoCirculacion buscarPorId(Integer id) {
        log.info("Buscando permiso con id: {}", id);
        return permisoCirculacionRepository.findById(id).orElse(null);
    }

    public List<PermisoCirculacion> buscarpordiasvigencia(Integer diasVigencia) {
        log.info("Buscandopor dias de vigencia: {}", diasVigencia);
        return permisoCirculacionRepository.findbydiasVigencia(diasVigencia);
    }

    public List<PermisoCirculacion> buscarPorPatente(String patente) {
        log.info("Buscando permisos por patente: {}", patente);
        return permisoCirculacionRepository.findByPatente(patente);
    }

    public List<PermisoCirculacion> buscarPorIdTramite(Integer idTramite) {
        log.info("Buscando permisos por tramite: {}", idTramite);
        return permisoCirculacionRepository.findByIdTramite(idTramite);
    }

    public Map<String, Object> obtenerPermisoCompleto(Integer id) {
        log.info("Obteniendo permiso completo con id: {}", id);
        PermisoCirculacion permiso = permisoCirculacionRepository.findById(id).orElse(null);
        if (permiso == null) {
            log.warn("Permiso con id {} no encontrado", id);
            return null;
        }
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("permiso", permiso);

        try {
            Object vehiculo = vehiculoClient.getVehiculoByPatente(permiso.getPatente());
            resultado.put("vehiculo", vehiculo);
            log.info("Vehiculo obtenido para patente: {}", permiso.getPatente());
        } catch (Exception e) {
            log.error("Error al obtener vehiculo: {}", e.getMessage());
            resultado.put("vehiculo", "Servicio no disponible");
        }

        return resultado;
    }

    public PermisoCirculacion agregarPermiso(PermisoCirculacion nuevo) {
        try {
            log.info("Creando permiso para patente: {}", nuevo.getPatente());
            return permisoCirculacionRepository.save(nuevo);
        } catch (Exception e) {
            log.error("Error al crear permiso: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarPermiso(Integer id) {
        try {
            if (permisoCirculacionRepository.existsById(id)) {
                log.info("Eliminando permiso con id: {}", id);
                permisoCirculacionRepository.deleteById(id);
                return true;
            } else {
                log.warn("Permiso con id {} no encontrado", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar permiso: {}", e.getMessage());
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
                log.info("Actualizando permiso con id: {}", id);
                return permisoCirculacionRepository.save(permiso);
            } else {
                log.warn("Permiso con id {} no encontrado para actualizar", id);
                return null;
            }
        } catch (Exception e) {
            log.error("Error al actualizar permiso: {}", e.getMessage());
            return null;
        }
    }
}