package com.CV.ApiCV.services;

import java.util.List;

import com.CV.ApiCV.dtos.CvRequestDto;
import com.CV.ApiCV.dtos.CvResponseDto;

public interface CvService {

    CvResponseDto crearParaAlumno(Long idAlumno, CvRequestDto datos);

    CvResponseDto crearParaProfesor(Long idProfesor, CvRequestDto datos);

    List<CvResponseDto> listarPorAlumno(Long idAlumno);

    List<CvResponseDto> listarPorProfesor(Long idProfesor);

    CvResponseDto buscarPorId(Long idCv);

    CvResponseDto actualizar(Long idCv, CvRequestDto datos);

    void eliminarPorId(Long idCv);
}
