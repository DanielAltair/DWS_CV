package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.ProfesorRequestDto;
import com.CV.ApiCV.dtos.ProfesorResponseDto;

public interface ProfesorService {
    ProfesorResponseDto crear(ProfesorRequestDto datos);

    List<ProfesorResponseDto> listar();

    ProfesorResponseDto buscarPorId(Long id);

    ProfesorResponseDto actualizar(Long id, ProfesorRequestDto datos);

    void eliminarPorId(Long id);
}
