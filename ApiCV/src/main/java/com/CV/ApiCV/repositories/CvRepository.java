package com.CV.ApiCV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Cv;

public interface CvRepository extends JpaRepository<Cv, Long> {
    
}
