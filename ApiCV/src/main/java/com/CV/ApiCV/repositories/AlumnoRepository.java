package com.CV.ApiCV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    
}
