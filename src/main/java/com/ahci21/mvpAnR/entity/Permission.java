package com.ahci21.mvpAnR.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel
public class Permission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idpermission;
    
    @NotNull
    private String permission_name;
    
    private String permission_description;
    
    @NotNull
    private int permisison_active;
    
    @ManyToMany(mappedBy = "permissions")
    Set<Role> permissionAssigned;

    public int getIdpermission() {
        return idpermission;
    }

    public void setIdpermission(int idpermission) {
        this.idpermission = idpermission;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_description() {
        return permission_description;
    }

    public void setPermission_description(String permission_description) {
        this.permission_description = permission_description;
    }

    public int getPermisison_active() {
        return permisison_active;
    }

    public void setPermisison_active(int permisison_active) {
        this.permisison_active = permisison_active;
    }

    public Set<Role> getPermissionAssigned() {
        return permissionAssigned;
    }

    public void setPermissionAssigned(Set<Role> permissionAssigned) {
        this.permissionAssigned = permissionAssigned;
    }
    
    @Override
    public String toString() {
        return "{permissionID: "+this.idpermission
                +" - name: "+this.permission_name
                +" - description: "+this.permission_description
                +" - active: "+this.permisison_active   
                +"}";
    }
    
}
