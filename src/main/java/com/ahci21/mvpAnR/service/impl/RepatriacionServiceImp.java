package com.ahci21.mvpAnR.service.impl;



import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.PersonaRepatriacionDto;
import com.ahci21.mvpAnR.entity.Persona;
import com.ahci21.mvpAnR.entity.Repatriacion;
import com.ahci21.mvpAnR.repository.PersonaRepository;
import com.ahci21.mvpAnR.repository.RepatriacionRepository;
import com.ahci21.mvpAnR.service.RepatriacionService;

@Service
public class RepatriacionServiceImp implements RepatriacionService{
    
    @Autowired
    RepatriacionRepository repRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Override
    public ResponseEntity<Repatriacion> getRepatriacion(int id) {

        Repatriacion repatriacion = new Repatriacion();
        
        try {
            repatriacion  = repRepository.findById(id).get();
            return ResponseEntity.ok(repatriacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
        
    }
    
    @Override
    public ResponseEntity<Repatriacion> setPersonaRepatriacion(PersonaRepatriacionDto repatriacionDto){
        List<Persona> listPersona = personaRepository.findAllById(repatriacionDto.getPersonas());
        
        
        
        Repatriacion repatriacion = new Repatriacion();
        try {
            repatriacion = repRepository.findById(repatriacionDto.getId()).get();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        Set<Persona> hashPersona = new HashSet<Persona>(); 
        
        try {
            for (Persona x : listPersona) 
                hashPersona.add(x); 
            repatriacion.setPersonasReapatriadas(hashPersona);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        repRepository.save(repatriacion);
        
        return ResponseEntity.ok(repatriacion);
    }
}
