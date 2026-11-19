package com.CV.ApiCV.dtos;

import java.time.LocalDate;

public record ExperienciaRequestDto(
    String nombre,
    String puesto,
    String descripcion,
    LocalDate fechaInicio,
    LocalDate fechaFin
) {}
