package com.ahci21.mvpAnR.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ahci21.mvpAnR.entity.Persona;
import com.ahci21.mvpAnR.entity.Repatriacion;
import com.ahci21.mvpAnR.repository.DocumentosPersonalesRepository;
import com.ahci21.mvpAnR.repository.PersonaRepository;
import com.ahci21.mvpAnR.repository.RasgosRepository;
import com.ahci21.mvpAnR.repository.RepatriacionRepository;
import com.ahci21.mvpAnR.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    RepatriacionRepository repaRepository;
    
    @Autowired
    RasgosRepository rasgosRepository;
    
    @Autowired
    DocumentosPersonalesRepository documentosPersonalesRepository;
    
    @Override
    public ResponseEntity<List<Persona>> consultaPersona(String query) {
        
        List<Persona> found = new ArrayList<>();
        
        //Find by name
        found.addAll(personaRepository.findAllBynombreUno(query));
        
        //Find by lastname
        found.addAll(personaRepository.findAllByapellidoPaterno(query));
        
        //Find by lastname
        found.addAll(personaRepository.findAllByapellidoMaterno(query));
        
        //Find by DUI
        try {
            found.add(documentosPersonalesRepository.findByDui(query).get().getPersona());
        } catch (Exception e) {
        }
        
               
        //Find by Partida
        
        try {
            found.add(documentosPersonalesRepository.findByPartida(query).get().getPersona());
        } catch (Exception e) {
        }
        
        
        //Find by Pasaporte
        
        try {
            found.add(documentosPersonalesRepository.findByPasaporte(query).get().getPersona());
        } catch (Exception e) {
        }

        //Fin by last_name
        
//        found.addAll();
        
        
        return ResponseEntity.ok(found);
    }
    
    @Override
    public ResponseEntity<List<Persona>> consultaPersonas() {
        return ResponseEntity.ok(personaRepository.findAll());
    }
    
    @Override   
    public ResponseEntity<String> crearPersona(Persona persona){
        personaRepository.save(persona);
        return new ResponseEntity<String>("Persona: "+ persona.toString(), null, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Persona> asignarRepatriacionPersona(int personaId,int repatriacionId){
        
        Repatriacion repatriacion = new Repatriacion();
        Persona persona = new Persona();
        Set<Repatriacion> setRepatriacion = new HashSet<>();
        
        try {
            persona = personaRepository.findById(personaId).get();                    
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        try {
            repatriacion = repaRepository.findById(repatriacionId).get();
            setRepatriacion.add(repatriacion);
        } catch (Exception e) {
            // TODO: handle exception
        }

        persona.setRepatriacion(setRepatriacion);
        
        personaRepository.save(persona);
        
        
        return ResponseEntity.ok(persona);
        
    }
    
}
