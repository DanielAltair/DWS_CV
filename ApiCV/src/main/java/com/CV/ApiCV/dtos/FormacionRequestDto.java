package com.CV.ApiCV.dtos;

import java.time.LocalDate;

public record FormacionRequestDto(

    String titulo,
    String centro,
    LocalDate fechaInicio,
    LocalDate fechaFin
) 
{}
