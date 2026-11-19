package com.CV.ApiCV.dtos;

import java.time.LocalDate;

public record FormacionResponseDto(
    Long idFormacion,
    String titulo,
    String centro,
    LocalDate fechaInicio,
    LocalDate fechaFin,
    Long idCv
) {}
