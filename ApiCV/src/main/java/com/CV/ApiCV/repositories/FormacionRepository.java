package com.CV.ApiCV.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Formacion;

public interface FormacionRepository extends JpaRepository<Formacion, Long> {
    List<Formacion> findByCv_IdCv(Long idCv);
}
