package com.ahci21.mvpAnR.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(position = -1, required = false, value="It is autogenerated",hidden = true)
    private int iduser;
    
    @NotNull
    private String username;
    
    @NotNull
    private String user_fullname;
    
    private Date last_login;

    @NotNull
    private String password;
    
    @NotNull
    private String email;
    
    @NotNull
    private int active;
    
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "iduser"),
        inverseJoinColumns = @JoinColumn(name = "idrole"))
    Set<Role> roles;

    
    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "{user id: "+this.iduser
                +" - username: "+this.username
                +" - user full name: "+this.user_fullname
                +" - user full name: "+this.user_fullname
                +" - last login: "+this.last_login
                +" - active: "+this.active
                +" - Roles: "+this.roles
                +"}";
    }
    
    
    
    
    
}
