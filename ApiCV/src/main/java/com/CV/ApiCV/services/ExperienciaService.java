package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.ExperienciaRequestDto;
import com.CV.ApiCV.dtos.ExperienciaResponseDto;

public interface ExperienciaService {
    ExperienciaResponseDto crear(Long idCv, ExperienciaRequestDto datos);

    List<ExperienciaResponseDto> listarPorCv(Long idCv);

    ExperienciaResponseDto buscarPorId(Long idExperiencia);

    ExperienciaResponseDto actualizar(Long idExperiencia, ExperienciaRequestDto datos);

    void eliminarPorId(Long idExperiencia);
}
