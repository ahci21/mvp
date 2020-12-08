package com.ahci21.mvpAnR.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.PersonaRepatriacionDto;
import com.ahci21.mvpAnR.entity.Persona;


@Service
public interface PersonaService {

    public ResponseEntity<List<Persona>> consultaPersona(String query);
    
    public ResponseEntity<List<Persona>> consultaPersonas();    

    public ResponseEntity<String> crearPersona(Persona persona);
    
    public ResponseEntity<Persona> asignarRepatriacionPersona(int personaId,int repatriacionId);
}
