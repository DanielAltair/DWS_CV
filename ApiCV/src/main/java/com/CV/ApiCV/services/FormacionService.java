package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.FormacionRequestDto;
import com.CV.ApiCV.dtos.FormacionResponseDto;

public interface FormacionService {
    FormacionResponseDto crear(Long idCv, FormacionRequestDto datos);

    List<FormacionResponseDto> listarPorCv(Long idCv);

    FormacionResponseDto buscarPorId(Long idFormacion);

    FormacionResponseDto actualizar(Long idFormacion, FormacionRequestDto datos);

    void eliminarPorId(Long idFormacion);
}
