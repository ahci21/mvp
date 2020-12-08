package com.ahci21.mvpAnR.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ahci21.mvpAnR.entity.Persona;
import com.ahci21.mvpAnR.service.impl.PersonaServiceImpl;

@RestController
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;
    
    @CrossOrigin
    @GetMapping("/persona")
    public ResponseEntity<List<Persona>> buscarPersona(@RequestParam String query) {
        return personaService.consultaPersona(query);
    }    
    
    @CrossOrigin
    @GetMapping("/persona/todas")
    public ResponseEntity<List<Persona>> buscarPersonas() {
        return personaService.consultaPersonas();
    }
    
    @PostMapping("/persona")
    public ResponseEntity<String> agregarPersona(@RequestBody Persona persona){
        return personaService.crearPersona(persona);
    }
    
    @PostMapping("/persona/asignar/repatriacion")
    public ResponseEntity<Persona> asignarRepatriacionPersona(@RequestParam int personaId,@RequestParam int repatriacionId){
        return personaService.asignarRepatriacionPersona(personaId, repatriacionId);
    }
    
    
}
