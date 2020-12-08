package com.ahci21.mvpAnR.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rasgos {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idrasgos;

    @NotNull
    private String estatura;

    @NotNull
    private String color_ojos;

    @NotNull
    private String color_cabello;

    @NotNull
    private String color_piel;
    
    private String peso;

    @NotNull
    private String señas_particulares;
    
    @JsonIgnore 
    @OneToOne(mappedBy = "rasgos")
    private Persona persona;

    public int getIdrasgos() {
        return idrasgos;
    }

    public void setIdrasgos(int idrasgos) {
        this.idrasgos = idrasgos;
    }

    public String getAltura() {
        return estatura;
    }

    public void setAltura(String altura) {
        this.estatura = altura;
    }

    public String getColor_ojos() {
        return color_ojos;
    }

    public void setColor_ojos(String color_ojos) {
        this.color_ojos = color_ojos;
    }

    public String getColor_cabello() {
        return color_cabello;
    }

    public void setColor_cabello(String color_cabello) {
        this.color_cabello = color_cabello;
    }

    public String getColor_piel() {
        return color_piel;
    }

    public void setColor_piel(String color_piel) {
        this.color_piel = color_piel;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSeñas_particulares() {
        return señas_particulares;
    }

    public void setSeñas_particulares(String señas_particulares) {
        this.señas_particulares = señas_particulares;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    @Override
    public String toString() {
        return "{rasgosID: "+this.idrasgos
                +" - estatura: "+this.estatura
                +" - color cabello: "+this.color_cabello
                +" - color ojos: "+this.color_ojos
                +" - color piel: "+this.color_piel
                +" - peso: "+this.peso
                +" - señas particulares: "+this.señas_particulares
                +"}";
    }
    
}
