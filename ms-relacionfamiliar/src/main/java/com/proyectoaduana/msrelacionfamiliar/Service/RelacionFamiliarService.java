package com.proyectoaduana.msrelacionfamiliar.Service;

import com.proyectoaduana.msrelacionfamiliar.Model.RelacionFamiliar;
import com.proyectoaduana.msrelacionfamiliar.Repository.RelacionFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class RelacionFamiliarService {

    @Autowired
    private RelacionFamiliarRepository relacionFamiliarRepository;
    private static final Logger log = LoggerFactory.getLogger(RelacionFamiliarService.class);

    public List<RelacionFamiliar> listarRelaciones() {
        log.info("Listando las relaciones familiares ");
        return relacionFamiliarRepository.findAll();
    }

    public RelacionFamiliar buscarPorId(Integer id) {
        return relacionFamiliarRepository.findById(id).orElse(null);
    }

    public List<RelacionFamiliar> buscarPorRutAdulto(String rutAdulto) {
        return relacionFamiliarRepository.findByRutAdulto(rutAdulto);
    }

    public List<RelacionFamiliar> buscarPorRutMenor(String rutMenor) {
        return relacionFamiliarRepository.findByRutMenor(rutMenor);
    }

    public RelacionFamiliar agregarRelacion(RelacionFamiliar nueva) {
        log.info("Creando Relacion Familiar: {}", nueva.getParentesco());
        return relacionFamiliarRepository.save(nueva);
    }

    public boolean eliminarRelacion(Integer id) {
        if (relacionFamiliarRepository.existsById(id)) {
            log.info("Eliminando relacion familiar : {}", id);
            relacionFamiliarRepository.deleteById(id);
            return true;
        } else {
            log.warn("relacion familiar {} no encontrado", id);
            return false;
        }
    }

    public RelacionFamiliar actualizarRelacion(Integer id, RelacionFamiliar nueva) {
        if (relacionFamiliarRepository.existsById(id)) {
            RelacionFamiliar relacion = relacionFamiliarRepository.findById(id).get();
            relacion.setParentesco(nueva.getParentesco());
            relacion.setRutAdulto(nueva.getRutAdulto());
            relacion.setRutMenor(nueva.getRutMenor());
            relacionFamiliarRepository.save(relacion);
            return relacion;
        } else {
            return null;
        }
    }
}
