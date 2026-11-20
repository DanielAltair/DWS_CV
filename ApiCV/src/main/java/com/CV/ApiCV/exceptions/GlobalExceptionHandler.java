package com.CV.ApiCV.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(DatosInvalidosException.class)
    public ProblemDetail manejarDatosInvalidos(DatosInvalidosException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST, 
            ex.getMessage()
        );

        problem.setTitle("Datos no válidos");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(AlumnoNotFoundException.class)
    public ProblemDetail manejarAlumnoNoEncontrado(AlumnoNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Alumno no encontrado");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(ProfesorNotFoundException.class)
        public ProblemDetail manejarProfesorNoEncontrado(ProfesorNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Profesor no encontrado");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(CvNotFoundException.class)
    public ProblemDetail manejarCvNoEncontrado(CvNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("CV no encontrado");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(FormacionNotFoundException.class)
    public ProblemDetail manejarFormacionNoEncontrada(FormacionNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Formación no encontrada");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(ExperienciaNotFoundException.class)
    public ProblemDetail manejarExperienciaNoEncontrada(ExperienciaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Experiencia no encontrada");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(CompetenciaNotFoundException.class)
    public ProblemDetail manejarCompetenciaNoEncontrada(CompetenciaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Competencia no encontrada");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(AptitudNotFoundException.class)
    public ProblemDetail manejarAptitudNoEncontrada(AptitudNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Aptitud no encontrada");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(IdiomaNotFoundException.class)
    public ProblemDetail manejarIdiomaNoEncontrado(IdiomaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problem.setTitle("Idioma no encontrado");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }
}
