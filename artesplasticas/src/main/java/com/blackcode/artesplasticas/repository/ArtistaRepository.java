package com.blackcode.artesplasticas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackcode.artesplasticas.model.ArtistaModel;

public interface ArtistaRepository extends JpaRepository<ArtistaModel, Long > {
  


}
