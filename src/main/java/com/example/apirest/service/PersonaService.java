package com.example.apirest.service;

import com.example.apirest.model.Persona;
import com.example.apirest.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id){
        return personaRepository.findById(id);
    }

    public Persona guardarPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Persona actualizarPersona(Long id, Persona personaDetalles){
        return personaRepository.findById(id).map(persona -> {
            persona.setNombre(personaDetalles.getNombre());
            persona.setApellido(personaDetalles.getApellido());
            persona.setEmail(personaDetalles.getEmail());
            persona.setTelefono(personaDetalles.getTelefono());
            return personaRepository.save(persona);
        }).orElse(null);
    }

    public boolean eliminarPersona(Long id){
        return personaRepository.findById(id).map(persona -> {
            personaRepository.delete(persona);
            return true;
        }).orElse(false);
    }
}
