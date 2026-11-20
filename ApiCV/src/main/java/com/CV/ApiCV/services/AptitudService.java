package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.AptitudRequestDto;
import com.CV.ApiCV.dtos.AptitudResponseDto;

public interface AptitudService {
    
    AptitudResponseDto crear(Long idCv, AptitudRequestDto datos);

    List<AptitudResponseDto> listarPorCv(Long idCv);

    AptitudResponseDto buscarPorId(Long idAptitud);

    AptitudResponseDto actualizar(Long idAptitud, AptitudRequestDto datos);

    void eliminarPorId(Long idAptitud);
}
