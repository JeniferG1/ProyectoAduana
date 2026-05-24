package com.proyectoaduana.mstramitefronterizo.Service;

import com.proyectoaduana.mstramitefronterizo.Model.TramiteFronterizo;
import com.proyectoaduana.mstramitefronterizo.Repository.TramiteFronterizoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TramiteFronterizoService {

    @Autowired
    private TramiteFronterizoRepository tramiteFronterizoRepository;
    private static final Logger log = LoggerFactory.getLogger(TramiteFronterizoService.class);

    public List<TramiteFronterizo> listarTramites() {
        log.info("Listando todos los tramites fronterizos ");
        return tramiteFronterizoRepository.findAll();
    }

    public TramiteFronterizo buscarPorId(Integer id) {
        return tramiteFronterizoRepository.findById(id).orElse(null);
    }

    public List<TramiteFronterizo> buscarPorRutPasajero(String rutPasajero) {
        return tramiteFronterizoRepository.findByRutPasajero(rutPasajero);
    }

    public List<TramiteFronterizo> buscarPorIdPaso(Integer idPaso) {
        return tramiteFronterizoRepository.findByIdPaso(idPaso);
    }

    public List<TramiteFronterizo> buscarPorIdFuncionario(Integer idFuncionario) {
        return tramiteFronterizoRepository.findByIdFuncionario(idFuncionario);
    }

    public TramiteFronterizo agregarTramite(TramiteFronterizo nuevo) {
        log.info("Creando Tramite Fronterizo: {}", nuevo.getRutPasajero());
        return tramiteFronterizoRepository.save(nuevo);
    }

    public boolean eliminarTramite(Integer id) {
        if (tramiteFronterizoRepository.existsById(id)) {
            log.info("Eliminando tramite fronterizo : {}", id);
            tramiteFronterizoRepository.deleteById(id);
            return true;
        } else {
            log.warn("tramite fronterizo {} no encontrado", id);
            return false;
        }
    }

    public TramiteFronterizo actualizarTramite(Integer id, TramiteFronterizo nuevo) {
        if (tramiteFronterizoRepository.existsById(id)) {
            TramiteFronterizo tramite = tramiteFronterizoRepository.findById(id).get();
            tramite.setFechaHora(nuevo.getFechaHora());
            tramite.setSentido(nuevo.getSentido());
            tramite.setIdPaso(nuevo.getIdPaso());
            tramite.setRutPasajero(nuevo.getRutPasajero());
            tramite.setIdFuncionario(nuevo.getIdFuncionario());
            tramiteFronterizoRepository.save(tramite);
            return tramite;
        } else {
            return null;
        }
    }
}
