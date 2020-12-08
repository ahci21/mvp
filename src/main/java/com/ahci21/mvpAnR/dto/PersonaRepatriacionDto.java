package com.ahci21.mvpAnR.dto;

import java.util.List;

public class PersonaRepatriacionDto {
    private List<Integer> personas;
    private int id;
    public Iterable<Integer> getPersonas() {
        return personas;
    }
    public void setPersonas(List<Integer> personas) {
        this.personas = personas;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
}
