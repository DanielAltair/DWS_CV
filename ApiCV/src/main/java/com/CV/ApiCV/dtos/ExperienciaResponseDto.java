package com.CV.ApiCV.dtos;

import java.time.LocalDate;

public record ExperienciaResponseDto(
    Long idExperiencia,
    String nombre,
    String puesto,
    String descripcion,
    LocalDate fechaInicio,
    LocalDate fechaFin,
    Long idCv
) {}
