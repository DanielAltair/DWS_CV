package com.CV.ApiCV.dtos;

public record AlumnoRequestDto(
    String nombreCompleto,
    String email,
    String telefono,
    String ciudad
) {}
