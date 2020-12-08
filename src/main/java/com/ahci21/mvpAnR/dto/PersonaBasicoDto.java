package com.ahci21.mvpAnR.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;


@ApiModel
public class PersonaBasicoDto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idpersona;
    
    @NotNull
    private String nombre_uno;
    
    private String nombre_otros;
    
    @NotNull
    private String apellido_paterno;
    
    @NotNull
    private String apellido_materno;
    
    private Date fecha_nacimiento;
    
    private String profesion;
    
    private String telefono;
    
    private String estado_civil;

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre_uno() {
        return nombre_uno;
    }

    public void setNombre_uno(String nombre_uno) {
        this.nombre_uno = nombre_uno;
    }

    public String getNombre_otros() {
        return nombre_otros;
    }

    public void setNombre_otros(String nombre_otros) {
        this.nombre_otros = nombre_otros;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }
    
    
    
    
}
