package com.proyectoaduana.mspersona.Service;

import com.proyectoaduana.mspersona.Model.Persona;
import com.proyectoaduana.mspersona.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    private static final Logger log = LoggerFactory.getLogger(PersonaService.class);

    public List<Persona> listarPersonas() {
        log.info("Listando todas las personas");
        return personaRepository.findAll();
    }


    public Persona buscarPorId(Integer id) {

        return personaRepository.findById(id).orElse(null);
    }

    public Persona buscarPorRutPasaporte(String rutPasaporte) {
        return personaRepository.findByRutPasaporte(rutPasaporte);
    }

    public Persona agregarPersona(Persona nueva) {
        log.info("Creando persona: {}", nueva.getRutPasaporte());
        return personaRepository.save(nueva);
    }

    public boolean eliminarPersona(Integer id) {
        if (personaRepository.existsById(id)) {
            log.info("Eliminando persona: {}", id);
            personaRepository.deleteById(id);
            return true;
        } else {
            log.warn("Persona {} no encontrada", id);
            return false;
        }
    }

    public Persona actualizarPersona(Integer id, Persona nueva) {
        if (personaRepository.existsById(id)) {
            Persona persona = personaRepository.findById(id).get();
            persona.setRutPasaporte(nueva.getRutPasaporte());
            persona.setNombres(nueva.getNombres());
            persona.setApellidos(nueva.getApellidos());
            persona.setFechaNacimiento(nueva.getFechaNacimiento());
            persona.setEsMenor(nueva.getEsMenor());
            personaRepository.save(persona);
            return persona;
        } else {
            return null;
        }
    }
}