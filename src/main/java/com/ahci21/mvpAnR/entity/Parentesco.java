package com.ahci21.mvpAnR.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Parentesco {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idparentezco;
    
    @NotNull
    private String relacion;
    
    @ManyToMany(mappedBy = "parentescos")
    Set<VinculoFamiliar> vinculos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_1")
    private DocumentosPersonales persona_1;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_2")
    private DocumentosPersonales persona_2;  
    
 
    
    
}
