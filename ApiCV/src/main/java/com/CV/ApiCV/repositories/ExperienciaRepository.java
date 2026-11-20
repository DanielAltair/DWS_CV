package com.CV.ApiCV.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Experiencia;

public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
    List<Experiencia> findByCv_IdCv(Long idCv);
}
