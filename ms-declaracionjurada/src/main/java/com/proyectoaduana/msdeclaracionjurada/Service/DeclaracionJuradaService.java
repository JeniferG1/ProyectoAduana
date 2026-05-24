package com.proyectoaduana.msdeclaracionjurada.Service;

import com.proyectoaduana.msdeclaracionjurada.Model.DeclaracionJurada;
import com.proyectoaduana.msdeclaracionjurada.Repository.DeclaracionJuradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class DeclaracionJuradaService {

    @Autowired
    private DeclaracionJuradaRepository declaracionJuradaRepository;
    private static final Logger log = LoggerFactory.getLogger(DeclaracionJuradaService.class);

    public List<DeclaracionJurada> listarDeclaraciones() {
        log.info("Listando todas las declaraciones");
        return declaracionJuradaRepository.findAll();
    }

    public DeclaracionJurada buscarPorId(Integer id) {
        return declaracionJuradaRepository.findById(id).orElse(null);
    }

    public List<DeclaracionJurada> buscarPorIdTramite(Integer idTramite) {
        return declaracionJuradaRepository.findByIdTramite(idTramite);
    }

    public DeclaracionJurada agregarDeclaracion(DeclaracionJurada nueva) {
        try {
            log.info("Creando declaracion: {}", nueva.getFirmaDigital());
            return declaracionJuradaRepository.save(nueva);

        } catch (Exception e) {
            log.error("Error al crear xxx: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarDeclaracion(Integer id) {
        try {
            if  (declaracionJuradaRepository.existsById(id))  {
                log.info("Eliminando declaracion con id: {}", id);
                declaracionJuradaRepository.deleteById(id);
                return true;
            } else {
                log.warn("declaracion {} no encontrada", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar  con id {}: {}", id, e.getMessage());
            return false;
        }
    }

    public DeclaracionJurada actualizarDeclaracion(Integer id, DeclaracionJurada nueva) {
        try {
            if (declaracionJuradaRepository.existsById(id)) {
                DeclaracionJurada declaracion = declaracionJuradaRepository.findById(id).get();
                declaracion.setFirmaDigital(nueva.getFirmaDigital());
                declaracion.setAlertaRiesgo(nueva.getAlertaRiesgo());
                declaracion.setIdTramite(nueva.getIdTramite());
                declaracionJuradaRepository.save(declaracion);
                return declaracion;
            } else {
                    return null;
            }

        } catch (Exception e) {
            log.error("Error al actualizar declaracion {}: {}", id, e.getMessage());
            return null;
        }

        }
    }






