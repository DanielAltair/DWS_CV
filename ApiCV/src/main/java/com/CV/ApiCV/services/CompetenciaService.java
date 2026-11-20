package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.CompetenciaRequestDto;
import com.CV.ApiCV.dtos.CompetenciaResponseDto;

public interface CompetenciaService {
    CompetenciaResponseDto crear(Long idCv, CompetenciaRequestDto datos);

    List<CompetenciaResponseDto> listarPorCv(Long idCv);

    CompetenciaResponseDto buscarPorId(Long idCompetencia);

    CompetenciaResponseDto actualizar(Long idCompetencia, CompetenciaRequestDto datos);

    void eliminarPorId(Long idCompetencia);
}
