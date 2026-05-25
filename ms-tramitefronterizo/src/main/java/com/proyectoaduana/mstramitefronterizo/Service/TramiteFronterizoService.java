package com.proyectoaduana.mstramitefronterizo.Service;

import com.proyectoaduana.mstramitefronterizo.Client.PersonaClient;
import com.proyectoaduana.mstramitefronterizo.Client.PasoFronterizoClient;
import com.proyectoaduana.mstramitefronterizo.Model.TramiteFronterizo;
import com.proyectoaduana.mstramitefronterizo.Repository.TramiteFronterizoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TramiteFronterizoService {

    private static final Logger log = LoggerFactory.getLogger(TramiteFronterizoService.class);

    @Autowired
    private TramiteFronterizoRepository tramiteFronterizoRepository;

    @Autowired
    private PersonaClient personaClient;

    @Autowired
    private PasoFronterizoClient pasoFronterizoClient;

    public List<TramiteFronterizo> listarTramites() {
        log.info("Listando todos los tramites fronterizos");
        return tramiteFronterizoRepository.findAll();
    }

    public TramiteFronterizo buscarPorId(Integer id) {
        log.info("Buscando tramite con id: {}", id);
        return tramiteFronterizoRepository.findById(id).orElse(null);
    }

    public List<TramiteFronterizo> buscarPorRutPasajero(String rutPasajero) {
        log.info("Buscando tramites por rut: {}", rutPasajero);
        return tramiteFronterizoRepository.findByRutPasajero(rutPasajero);
    }

    public List<TramiteFronterizo> buscarPorIdPaso(Integer idPaso) {
        log.info("Buscando tramites por paso: {}", idPaso);
        return tramiteFronterizoRepository.findByIdPaso(idPaso);
    }

    public List<TramiteFronterizo> buscarPorIdFuncionario(Integer idFuncionario) {
        log.info("Buscando tramites por funcionario: {}", idFuncionario);
        return tramiteFronterizoRepository.findByIdFuncionario(idFuncionario);
    }

    public Map<String, Object> obtenerTramiteCompleto(Integer id) {
        log.info("Obteniendo tramite completo con id: {}", id);
        TramiteFronterizo tramite = tramiteFronterizoRepository.findById(id).orElse(null);
        if (tramite == null) {
            log.warn("Tramite con id {} no encontrado", id);
            return null;
        }
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("tramite", tramite);

        try {
            Object persona = personaClient.getPersonaByRut(tramite.getRutPasajero());
            resultado.put("persona", persona);
            log.info("Persona obtenida para rut: {}", tramite.getRutPasajero());
        } catch (Exception e) {
            log.error("Error al obtener persona: {}", e.getMessage());
            resultado.put("persona", "Servicio no disponible");
        }

        try {
            Object paso = pasoFronterizoClient.getPasoById(tramite.getIdPaso());
            resultado.put("paso", paso);
            log.info("Paso obtenido con id: {}", tramite.getIdPaso());
        } catch (Exception e) {
            log.error("Error al obtener paso: {}", e.getMessage());
            resultado.put("paso", "Servicio no disponible");
        }

        return resultado;
    }

    public TramiteFronterizo agregarTramite(TramiteFronterizo nuevo) {
        try {
            log.info("Creando tramite para pasajero: {}", nuevo.getRutPasajero());
            nuevo.setIdTramite(null);
            return tramiteFronterizoRepository.save(nuevo);
        } catch (Exception e) {
            log.error("Error al crear tramite: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarTramite(Integer id) {
        try {
            if (tramiteFronterizoRepository.existsById(id)) {
                log.info("Eliminando tramite con id: {}", id);
                tramiteFronterizoRepository.deleteById(id);
                return true;
            } else {
                log.warn("Tramite con id {} no encontrado", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar tramite: {}", e.getMessage());
            return false;
        }
    }

    public TramiteFronterizo actualizarTramite(Integer id, TramiteFronterizo nuevo) {
        try {
            if (tramiteFronterizoRepository.existsById(id)) {
                TramiteFronterizo tramite = tramiteFronterizoRepository.findById(id).get();
                tramite.setFechaHora(nuevo.getFechaHora());
                tramite.setSentido(nuevo.getSentido());
                tramite.setIdPaso(nuevo.getIdPaso());
                tramite.setRutPasajero(nuevo.getRutPasajero());
                tramite.setIdFuncionario(nuevo.getIdFuncionario());
                log.info("Actualizando tramite con id: {}", id);
                return tramiteFronterizoRepository.save(tramite);
            } else {
                log.warn("Tramite con id {} no encontrado para actualizar", id);
                return null;
            }
        } catch (Exception e) {
            log.error("Error al actualizar tramite: {}", e.getMessage());
            return null;
        }
    }
}