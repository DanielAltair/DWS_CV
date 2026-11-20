package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.AlumnoRequestDto;
import com.CV.ApiCV.dtos.AlumnoResponseDto;

public interface AlumnoService {
    
    AlumnoResponseDto crear(AlumnoRequestDto datos);

    List<AlumnoResponseDto> listar();

    AlumnoResponseDto buscarPorId(Long id);

    AlumnoResponseDto actualizar(Long id, AlumnoRequestDto datos);

    void eliminarPorId(Long id);
}
