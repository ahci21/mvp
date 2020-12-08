package com.ahci21.mvpAnR.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahci21.mvpAnR.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findAllBynombreUno(String nombre_uno);
    List<Persona> findAllBynombreOtros(String nombre_otros);
    List<Persona> findAllByapellidoPaterno(String apellido_paterno);
    List<Persona> findAllByapellidoMaterno(String apellido_materno);
    
}
