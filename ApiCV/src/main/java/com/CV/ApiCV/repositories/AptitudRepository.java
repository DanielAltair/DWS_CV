package com.CV.ApiCV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Aptitud;

public interface AptitudRepository extends JpaRepository<Aptitud, Long> {
    
}
