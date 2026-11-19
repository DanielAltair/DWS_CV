package com.CV.ApiCV.dtos;

public record ProfesorResponseDto(
    Long idProfesor,
    String nombreCompleto,
    String email,
    String telefono,
    String ciudad
) 

{}
