package com.ahci21.mvpAnR.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "documentos_personales")
public class DocumentosPersonales {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int iddocumentos_personales;
    
    private String dui;
    
    private String pasaporte;
    
    private String partida;
    
    @OneToOne(mappedBy = "documentos_personales")
    private Persona persona;

    public int getIddocumentos() {
        return iddocumentos_personales;
    }

    public void setIddocumentos(int iddocumentos) {
        this.iddocumentos_personales = iddocumentos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    @Override
    public String toString() {
        return "{documentid: "+this.iddocumentos_personales
                +" - DUI: "+this.dui
                +" - Pasaporte: "+this.pasaporte
                +" - Partida: "+this.partida
                +"}";
    }
    
    
}
