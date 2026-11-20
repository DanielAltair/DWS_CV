package com.CV.ApiCV.dtos;

import java.util.List;

public record CvResponseDto(
    Long idCv,
    String perfil,
    Long idAlumno,
    Long idProfesor,
    List<FormacionResponseDto> formaciones,
    List<ExperienciaResponseDto> experiencias,
    List<CompetenciaResponseDto> competencias,
    List<AptitudResponseDto> aptitudes,
    List<IdiomaResponseDto> idiomas
) {}