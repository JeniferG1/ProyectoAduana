package com.proyectoaduana.mspasofronterizo.Service;

import com.proyectoaduana.mspasofronterizo.Model.PasoFronterizo;
import com.proyectoaduana.mspasofronterizo.Repository.PasoFronterizoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PasoFronterizoService {

    @Autowired
    private PasoFronterizoRepository pasoFronterizoRepository;
    private static final Logger log = LoggerFactory.getLogger(PasoFronterizoService.class);

    public List<PasoFronterizo> listarPasos() {
        log.info("Listando todos pasos fronterizos");
        return pasoFronterizoRepository.findAll();
    }

    public PasoFronterizo buscarPorId(Integer id) {
        return pasoFronterizoRepository.findById(id).orElse(null);
    }

    public List<PasoFronterizo> buscarPorCodPais(Integer codPais) {
        return pasoFronterizoRepository.findByCodPais(codPais);
    }

    public PasoFronterizo buscarPorNombre(String nombrePaso) {
        return pasoFronterizoRepository.findByNombrePaso(nombrePaso);
    }

    public PasoFronterizo agregarPaso(PasoFronterizo nuevo) {
        try {
        log.info("Creando Paso fronterizo: {}", nuevo.getNombrePaso());
        return pasoFronterizoRepository.save(nuevo);
    }catch (Exception e) {
            log.error("Error al crear paso fronterizo: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarPaso(Integer id) {
        try {
        if (pasoFronterizoRepository.existsById(id)) {
            log.info("Eliminando paso fronterizo id: {}", id);
            pasoFronterizoRepository.deleteById(id);
            return true;
        } else {
            log.warn("Paso fronterizo {} no encontrado", id);
            return false;
        }
    } catch (Exception e) {
        log.error("Error al eliminar paso fronterizo con id {}: {}", id, e.getMessage());
        return false;
    }
    }


    public PasoFronterizo actualizarPaso(Integer id, PasoFronterizo nuevo) {
        try {
        if (pasoFronterizoRepository.existsById(id)) {
            PasoFronterizo paso = pasoFronterizoRepository.findById(id).get();
            paso.setNombrePaso(nuevo.getNombrePaso());
            paso.setCodPais(nuevo.getCodPais());
            pasoFronterizoRepository.save(paso);
            return paso;
        } else {
            return null;
        }
    } catch (Exception e) {
        log.error("Error al actualizar paso fronterizo con id {}: {}", id, e.getMessage());
        return null;
    }
}
}
