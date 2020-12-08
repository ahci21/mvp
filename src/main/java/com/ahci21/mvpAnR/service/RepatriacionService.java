package com.ahci21.mvpAnR.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.PersonaRepatriacionDto;
import com.ahci21.mvpAnR.entity.Repatriacion;

@Service
public interface RepatriacionService {
    ResponseEntity<Repatriacion> getRepatriacion(int id);   

    ResponseEntity<Repatriacion> setPersonaRepatriacion(PersonaRepatriacionDto perRep);
    
}
