package com.ahci21.mvpAnR.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Acreditacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idacreditacion;
    
    @NotNull
    private String acreditacion_folio;
    
    @NotNull
    private Date acreditacion_fecha;
    
    @JsonIgnore 
    @ManyToMany(mappedBy = "acreditaciones")
    Set<Persona> acreditados;
    
    
}
