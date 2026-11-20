package com.CV.ApiCV.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Idioma;

public interface IdiomaRepository extends JpaRepository<Idioma, Long>{
    List<Idioma> findByCv_IdCv(Long idCv);
}
