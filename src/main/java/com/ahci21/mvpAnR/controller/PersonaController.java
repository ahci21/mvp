package com.ahci21.mvpAnR.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahci21.mvpAnR.service.impl.PersonaServiceImpl;

@RestController
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;
    
    @GetMapping("/persona")
    public ResponseEntity<String> login(@RequestParam int id) {
        return personaService.consultaPersona(id);
    }
    
}
