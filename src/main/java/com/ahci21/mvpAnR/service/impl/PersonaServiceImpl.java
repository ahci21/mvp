package com.ahci21.mvpAnR.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.repository.PersonaRepository;
import com.ahci21.mvpAnR.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;
    
    @Override
    public ResponseEntity<String> consultaPersona(int personaId) {

        return new ResponseEntity<String>("Persona: "+personaRepository.findById(personaId).toString(), null, HttpStatus.ACCEPTED);
    }

}
