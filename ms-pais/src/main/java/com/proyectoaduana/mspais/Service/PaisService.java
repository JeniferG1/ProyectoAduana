package com.proyectoaduana.mspais.Service;

import com.proyectoaduana.mspais.Model.Pais;
import com.proyectoaduana.mspais.Repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;
    private static final Logger log = LoggerFactory.getLogger(PaisService.class);

    public List<Pais> listarPaises() {
        log.info("Listando todos los paises");
        return paisRepository.findAll();
    }

    public Pais buscarPorId(Integer id) {
        return paisRepository.findById(id).orElse(null);
    }

    public Pais buscarPorNombre(String nombrePais) {
        return paisRepository.findByNombrePais(nombrePais);
    }

    public Pais agregarPais(Pais nuevo) {
        try {
        log.info("Creando Pais: {}", nuevo.getNombrePais());
        return paisRepository.save(nuevo);
    }catch (Exception e) {
        log.error("Error al crear pais: {}", e.getMessage());
        return null;
    }
    }

    public boolean eliminarPais(Integer id) {
        try {
        if (paisRepository.existsById(id)) {
            paisRepository.deleteById(id);
            log.info("Eliminando pais: {}", id);
            return true;
        } else {
            log.warn("Pais {} no encontrado", id);
            return false;
        }
        } catch (Exception e) {
            log.error("Error al eliminar pais con id {}: {}", id, e.getMessage());
            return false;
        }
    }

    public Pais actualizarPais(Integer id, Pais nuevo) {
        try {
        if (paisRepository.existsById(id)) {
            Pais pais = paisRepository.findById(id).get();
            pais.setNombrePais(nuevo.getNombrePais());

            paisRepository.save(pais);
            return pais;
        } else {

            return null;
        }
        } catch (Exception e) {
            log.error("Error al actualizar pais con id {}: {}", id, e.getMessage());
            return null;
        }
    }
}