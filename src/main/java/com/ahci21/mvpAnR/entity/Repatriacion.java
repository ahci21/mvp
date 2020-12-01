package com.ahci21.mvpAnR.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Repatriacion {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idrepatriacion;
    
    private String numero_oficio;
    
    @NotNull    
    private String lugar_de_aseguramiento;
    
    @NotNull
    private Date fecha_de_notificacion;
    
    private String observaciones;
    
    @ManyToMany(mappedBy = "repatriacion")
    Set<Persona> personasReapatriadas;

    public int getIdrepatriacion() {
        return idrepatriacion;
    }

    public void setIdrepatriacion(int idrepatriacion) {
        this.idrepatriacion = idrepatriacion;
    }

    public String getNumero_oficio() {
        return numero_oficio;
    }

    public void setNumero_oficio(String numero_oficio) {
        this.numero_oficio = numero_oficio;
    }

    public String getLugar_de_aseguramiento() {
        return lugar_de_aseguramiento;
    }

    public void setLugar_de_aseguramiento(String lugar_de_aseguramiento) {
        this.lugar_de_aseguramiento = lugar_de_aseguramiento;
    }

    public Date getFecha_de_notificacion() {
        return fecha_de_notificacion;
    }

    public void setFecha_de_notificacion(Date fecha_de_notificacion) {
        this.fecha_de_notificacion = fecha_de_notificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<Persona> getPersonasReapatriadas() {
        return personasReapatriadas;
    }

    public void setPersonasReapatriadas(Set<Persona> personasReapatriadas) {
        this.personasReapatriadas = personasReapatriadas;
    }
    
    
    
}
