package com.ahci21.mvpAnR.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Persona {
    
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "iddocumentos_personales",
            referencedColumnName = "iddocumentos_personales")
    private DocumentosPersonales documentos_personales;
    
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(
            name = "idrasgos",
            referencedColumnName = "idrasgos")
    private Rasgos rasgos;
    
    @ManyToMany
    @JoinTable(
            name = "persona_repatriacion",
            joinColumns = @JoinColumn(name = "idpersona"),
            inverseJoinColumns = @JoinColumn(name = "idrepatriacion"))
    private Set<Repatriacion> repatriacion;
    
    @ManyToMany
    @JoinTable(
            name = "acreditados",
            joinColumns = @JoinColumn(name = "idpersona"),
            inverseJoinColumns = @JoinColumn(name = "idacreditacion"))
    private Set<Acreditacion> acreditaciones;
    
    @OneToMany(mappedBy = "persona_1")
    private List<Parentesco> parientes_1;
    
    @OneToMany(mappedBy = "persona_2")
    private List<Parentesco> parientes_2;

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

    public DocumentosPersonales getDocumentos_personales() {
        return documentos_personales;
    }

    public void setDocumentos_personales(DocumentosPersonales documentos_personales) {
        this.documentos_personales = documentos_personales;
    }

    public Rasgos getRasgos() {
        return rasgos;
    }

    public void setRasgos(Rasgos rasgos) {
        this.rasgos = rasgos;
    }

    public Set<Repatriacion> getRepatriacion() {
        return repatriacion;
    }

    public void setRepatriacion(Set<Repatriacion> repatriacion) {
        this.repatriacion = repatriacion;
    }

    public Set<Acreditacion> getAcreditaciones() {
        return acreditaciones;
    }

    public void setAcreditaciones(Set<Acreditacion> acreditaciones) {
        this.acreditaciones = acreditaciones;
    }

    public List<Parentesco> getParientes_1() {
        return parientes_1;
    }

    public void setParientes_1(List<Parentesco> parientes_1) {
        this.parientes_1 = parientes_1;
    }

    public List<Parentesco> getParientes_2() {
        return parientes_2;
    }

    public void setParientes_2(List<Parentesco> parientes_2) {
        this.parientes_2 = parientes_2;
    }
    
    @Override
    public String toString() {
        return "{"
                + "Nombre: "+this.getNombre_uno()+"\n"
                + "Apellido Paterno: "+this.getApellido_paterno()+"\n"
                + "Profesión: "+this.profesion
                        + "}";
    }
    
    
    
    
}
