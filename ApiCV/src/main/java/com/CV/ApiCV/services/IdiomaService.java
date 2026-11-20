package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.IdiomaRequestDto;
import com.CV.ApiCV.dtos.IdiomaResponseDto;

public interface IdiomaService {
    
    IdiomaResponseDto crear(Long idCv, IdiomaRequestDto datos);

    List<IdiomaResponseDto> listarPorCv(Long idCv);

    IdiomaResponseDto buscarPorId(Long idIdioma);

    IdiomaResponseDto actualizar(Long idIdioma, IdiomaRequestDto datos);

    void eliminarPorId(Long idIdioma);
}
