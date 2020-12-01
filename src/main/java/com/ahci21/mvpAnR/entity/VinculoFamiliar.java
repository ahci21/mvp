package com.ahci21.mvpAnR.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vinculo_familiar")
public class VinculoFamiliar {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idvinculo_familiar;
    
    @NotNull
    private String vinculo_familiar_folio;
    
    @NotNull
    private Date vinculo_familiar_fecha;
    
    @ManyToMany
    @JoinTable(
            name = "parentesco_vinculo_familiar",
            joinColumns = @JoinColumn(name = "idvinculo_familiar"),
            inverseJoinColumns = @JoinColumn(name = "idparentezco"))
    Set<Parentesco> parentescos;
    
    
    
    
}
