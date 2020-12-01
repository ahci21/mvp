package com.ahci21.mvpAnR.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface PersonaService {

    public ResponseEntity<String> consultaPersona(int personaId);
}
