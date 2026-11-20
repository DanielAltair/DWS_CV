package com.CV.ApiCV.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Aptitud;

public interface AptitudRepository extends JpaRepository<Aptitud, Long> {
    List<Aptitud> findByCv_IdCv(Long idCv);
}
