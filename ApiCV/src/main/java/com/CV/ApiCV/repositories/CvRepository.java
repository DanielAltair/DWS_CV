package com.CV.ApiCV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CV.ApiCV.entities.Cv;
import java.util.List;


public interface CvRepository extends JpaRepository<Cv, Long> {
List<Cv> findByAlumno_IdAlumno(Long idAlumno);

List<Cv> findByProfesor_IdProfesor(Long idProfesor);
}
