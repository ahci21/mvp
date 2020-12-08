package com.ahci21.mvpAnR.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahci21.mvpAnR.entity.DocumentosPersonales;

@Repository
public interface DocumentosPersonalesRepository extends JpaRepository<DocumentosPersonales, Integer>{

    Optional<DocumentosPersonales> findByDui(String dui);
    Optional<DocumentosPersonales> findByPartida(String partida);
    Optional<DocumentosPersonales> findByPasaporte(String pasaporte);
}
