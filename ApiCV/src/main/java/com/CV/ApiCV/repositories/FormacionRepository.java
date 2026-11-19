package com.CV.ApiCV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Formacion;

public interface FormacionRepository extends JpaRepository<Formacion, Long> {
    
}
