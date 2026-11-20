package com.CV.ApiCV.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Competencia;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    List<Competencia> findByCv_IdCv(Long idCv);
}
