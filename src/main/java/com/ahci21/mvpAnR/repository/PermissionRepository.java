package com.ahci21.mvpAnR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahci21.mvpAnR.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
