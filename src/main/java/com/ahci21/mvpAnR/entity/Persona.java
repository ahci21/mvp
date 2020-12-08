package com.ahci21.mvpAnR.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel
public class Persona {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idpersona;
    
    @Column(name = "nombre_uno")
    @NotNull
    private String nombreUno;
    
    @Column(name = "nombre_otros")
    private String nombreOtros;
    
    @Column(name = "apellido_paterno")
    @NotNull
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno")
    @NotNull
    private String apellidoMaterno;
    
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    private String profesion;
    
    private String telefono;
    
    @Column(name = "estado_civil")
    private String estadoCivil;
    
    
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
            name = "persona_reapatriacion",
            joinColumns = @JoinColumn(name = "idpersona"),
            inverseJoinColumns = @JoinColumn(name = "idrepatriacion"))
    private Set<Repatriacion> repatriacion;
    

    @ManyToMany
    @JoinTable(
            name = "acreditados",
            joinColumns = @JoinColumn(name = "idpersona"),
            inverseJoinColumns = @JoinColumn(name = "idacreditacion"))
    private Set<Acreditacion> acreditaciones;
    
    @JsonIgnore 
    @OneToMany(mappedBy = "persona_1")
    private List<Parentesco> parientes_1;
    
    @JsonIgnore 
    @OneToMany(mappedBy = "persona_2")
    private List<Parentesco> parientes_2;

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre_uno() {
        return nombreUno;
    }

    public void setNombre_uno(String nombre_uno) {
        this.nombreUno = nombre_uno;
    }

    public String getNombre_otros() {
        return nombreOtros;
    }

    public void setNombre_otros(String nombre_otros) {
        this.nombreOtros = nombre_otros;
    }

    public String getApellido_paterno() {
        return apellidoPaterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellidoPaterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellidoMaterno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellidoMaterno = apellido_materno;
    }

    public Date getFecha_nacimiento() {
        return fechaNacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fechaNacimiento = fecha_nacimiento;
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
        return estadoCivil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estadoCivil = estado_civil;
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
                + "Nombre 2: "+this.getNombre_otros()+"\n"
                + "Apellido Paterno: "+this.getApellido_paterno()+"\n"
                + "Apellido Materno: "+this.getApellido_materno()+"\n"
                + "Profesión: "+this.getProfesion()+"\n"
                + "Documentos extra: "+this.getDocumentos_personales()+"\n"
                + "Rasgos Particulares: "+this.getRasgos()
                
                        + "}";
    }
    
    
    
    
}
