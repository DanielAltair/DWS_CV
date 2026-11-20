package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.CompetenciaRequestDto;
import com.CV.ApiCV.dtos.CompetenciaResponseDto;
import com.CV.ApiCV.entities.Competencia;
import com.CV.ApiCV.entities.Cv;
import com.CV.ApiCV.exceptions.CompetenciaNotFoundException;
import com.CV.ApiCV.exceptions.CvNotFoundException;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.repositories.CompetenciaRepository;
import com.CV.ApiCV.repositories.CvRepository;
import com.CV.ApiCV.services.CompetenciaService;

@Service 
public class CompetenciaServiceImpl implements CompetenciaService{
     private final CompetenciaRepository competenciaRepository;
    private final CvRepository cvRepository;

    public CompetenciaServiceImpl(
        CompetenciaRepository competenciaRepository,
        CvRepository cvRepository
    ) {
        this.competenciaRepository = competenciaRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public CompetenciaResponseDto crear(Long idCv, CompetenciaRequestDto datos) {
         if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException("El nombre de la competencia es obligatorio");
        }

        Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        Competencia competencia = new Competencia();
        competencia.setNombre(datos.nombre());
        competencia.setCv(cv);

        Competencia guardada = competenciaRepository.save(competencia);

        return mapToResponseDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompetenciaResponseDto> listarPorCv(Long idCv) {
        if (!cvRepository.existsById(idCv)) {
            throw new CvNotFoundException("CV no encontrado con id: " + idCv);
        }

        List<Competencia> competencias = competenciaRepository.findByCv_IdCv(idCv);

        return competencias.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompetenciaResponseDto buscarPorId(Long idCompetencia) {
        Competencia competencia = competenciaRepository.findById(idCompetencia).orElseThrow(() ->
            new CompetenciaNotFoundException(
                "Competencia no encontrada con id: " + idCompetencia
            )
        );

        return mapToResponseDto(competencia);
    }

    @Override
    @Transactional
    public CompetenciaResponseDto actualizar(Long idCompetencia, CompetenciaRequestDto datos) {
        Competencia competencia = competenciaRepository.findById(idCompetencia).orElseThrow(() ->
            new CompetenciaNotFoundException(
                "Competencia no encontrada con id: " + idCompetencia
            )
        );

        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException("El nombre de la competencia es obligatorio");
        }

        competencia.setNombre(datos.nombre());

        Competencia actualizada = competenciaRepository.save(competencia);

        return mapToResponseDto(actualizada);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long idCompetencia) {
        Competencia competencia = competenciaRepository.findById(idCompetencia).orElseThrow(() ->
            new CompetenciaNotFoundException(
                "Competencia no encontrada con id: " + idCompetencia
            )
        );

        competenciaRepository.delete(competencia);
    }

    private CompetenciaResponseDto mapToResponseDto(Competencia competencia) {
        return new CompetenciaResponseDto(
            competencia.getIdCompetencia(),
            competencia.getNombre(),
            competencia.getCv().getIdCv()
        );
    }
}
