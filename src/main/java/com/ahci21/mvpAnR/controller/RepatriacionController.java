package com.ahci21.mvpAnR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahci21.mvpAnR.dto.PersonaRepatriacionDto;
import com.ahci21.mvpAnR.entity.Repatriacion;
import com.ahci21.mvpAnR.service.impl.RepatriacionServiceImp;

@RestController
public class RepatriacionController {

    @Autowired
    RepatriacionServiceImp repaService;
    
    @GetMapping("/repatriacion")
    public ResponseEntity<Repatriacion> getRepatriacion(@RequestParam int id){
        return repaService.getRepatriacion(id);
    }

    @PostMapping("/repatriacion")
    public ResponseEntity<Repatriacion> setPersonasRepatriacion(@RequestBody PersonaRepatriacionDto perRep){
        return repaService.setPersonaRepatriacion(perRep);
    }
    
}
