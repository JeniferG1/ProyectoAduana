package com.proyectoaduana.mspersona.Service;

import com.proyectoaduana.mspersona.Model.Persona;
import com.proyectoaduana.mspersona.Repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private static final Logger log = LoggerFactory.getLogger(PersonaService.class);

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> listarPersonas() {
        log.info("Listando todas las personas");
        return personaRepository.findAll();
    }

    public Persona buscarPorId(Integer id) {
        log.info("Buscando persona con id: {}", id);
        return personaRepository.findById(id).orElse(null);
    }

    public Persona buscarPorRutPasaporte(String rutPasaporte) {
        log.info("Buscando persona con RUT: {}", rutPasaporte);
        return personaRepository.findByRutPasaporte(rutPasaporte);
    }

    public Persona agregarPersona(Persona nueva) {
        try {
            log.info("Creando persona con RUT: {}", nueva.getRutPasaporte());
            nueva.setIdPersona(null);
            return personaRepository.save(nueva);
        } catch (Exception e) {
            log.error("Error al crear persona: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarPersona(Integer id) {
        try {
            if (personaRepository.existsById(id)) {
                log.info("Eliminando persona con id: {}", id);
                personaRepository.deleteById(id);
                return true;
            } else {
                log.warn("Persona con id {} no encontrada", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar persona con id {}: {}", id, e.getMessage());
            return false;
        }
    }

    public Persona actualizarPersona(Integer id, Persona nueva) {
        try {
            if (personaRepository.existsById(id)) {
                Persona persona = personaRepository.findById(id).get();
                persona.setRutPasaporte(nueva.getRutPasaporte());
                persona.setNombres(nueva.getNombres());
                persona.setApellidos(nueva.getApellidos());
                persona.setFechaNacimiento(nueva.getFechaNacimiento());
                persona.setEsMenor(nueva.getEsMenor());
                log.info("Actualizando persona con id: {}", id);
                return personaRepository.save(persona);
            } else {
                log.warn("Persona con id {} no encontrada para actualizar", id);
                return null;
            }
        } catch (Exception e) {
            log.error("Error al actualizar persona con id {}: {}", id, e.getMessage());
            return null;
        }
    }
}