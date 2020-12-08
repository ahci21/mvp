package com.ahci21.mvpAnR.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel
public class Role {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idrole;
    
    @NotNull
    private String role_name;
    
    private String role_description;

    @NotNull
    private int role_active;
    
    @JsonIgnore 
    @ManyToMany(mappedBy = "roles")
    Set<User> usersAssigned;
    
    @ManyToMany
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "idrole"),
        inverseJoinColumns = @JoinColumn(name = "idpermission"))
    Set<Permission> permissions;

    public Set<User> getUsersAssigned() {
        return usersAssigned;
    }

    public void setUsersAssigned(Set<User> usersAssigned) {
        this.usersAssigned = usersAssigned;
    }

    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    public int getRole_active() {
        return role_active;
    }

    public void setRole_active(int role_active) {
        this.role_active = role_active;
    }
    
    @Override
    public String toString() {
        return "{roleid: "+this.idrole
                +" - active: "+this.role_active
                +" - description: "+this.role_description
                +" - name: "+this.role_name   
                +"}";
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    
    
    
    
}
