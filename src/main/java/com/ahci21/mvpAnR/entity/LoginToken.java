package com.ahci21.mvpAnR.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "tokens")
@Component
public class LoginToken {

    @Id
    private Integer id;
    
    private LocalDateTime expirationDate;
    
    @NotNull
    private int iduser;
    
    @NotNull
    private int[] idPermission;
    

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int[] getIdpermission() {
        return idPermission;
    }

    public void setIdpermission(int[] idpermission) {
        this.idPermission = idpermission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
