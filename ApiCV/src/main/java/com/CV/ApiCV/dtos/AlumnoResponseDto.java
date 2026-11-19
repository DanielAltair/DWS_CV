package com.CV.ApiCV.dtos;

public record AlumnoResponseDto(

    Long idAlumno,
    String nombreCompleto,
    String email,
    String telefono,
    String ciudad
) {}
