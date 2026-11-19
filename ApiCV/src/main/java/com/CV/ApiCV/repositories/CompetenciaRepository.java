package com.CV.ApiCV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Competencia;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    
}
