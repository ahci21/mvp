package com.ahci21.mvpAnR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahci21.mvpAnR.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
