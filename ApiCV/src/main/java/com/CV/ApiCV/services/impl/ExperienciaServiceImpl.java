package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.ExperienciaRequestDto;
import com.CV.ApiCV.dtos.ExperienciaResponseDto;
import com.CV.ApiCV.entities.Cv;
import com.CV.ApiCV.entities.Experiencia;
import com.CV.ApiCV.exceptions.CvNotFoundException;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.exceptions.ExperienciaNotFoundException;
import com.CV.ApiCV.repositories.CvRepository;
import com.CV.ApiCV.repositories.ExperienciaRepository;
import com.CV.ApiCV.services.ExperienciaService;

@Service 
public class ExperienciaServiceImpl implements ExperienciaService{
     private final ExperienciaRepository experienciaRepository;
    private final CvRepository cvRepository;

    public ExperienciaServiceImpl(
        ExperienciaRepository experienciaRepository,
        CvRepository cvRepository
    ) {
        this.experienciaRepository = experienciaRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public ExperienciaResponseDto crear(Long idCv, ExperienciaRequestDto datos) {
        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException("El nombre de empresa o proyecto es obligatorio");
        }

        if (datos.puesto() == null || datos.puesto().isBlank()) {
            throw new DatosInvalidosException("El puesto es obligatorio");
        }

        if (datos.fechaInicio() == null) {
            throw new DatosInvalidosException("La fecha de inicio es obligatoria");
        }

        Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        Experiencia experiencia = new Experiencia();
        experiencia.setNombre(datos.nombre());
        experiencia.setPuesto(datos.puesto());
        experiencia.setDescripcion(datos.descripcion());
        experiencia.setFechaInicio(datos.fechaInicio());
        experiencia.setFechaFin(datos.fechaFin());
        experiencia.setCv(cv);

        Experiencia guardada = experienciaRepository.save(experiencia);

        return mapToResponseDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienciaResponseDto> listarPorCv(Long idCv) {
        if (!cvRepository.existsById(idCv)) {
            throw new CvNotFoundException("CV no encontrado con id: " + idCv);
        }

        List<Experiencia> experiencias = experienciaRepository.findByCv_IdCv(idCv);

        return experiencias.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExperienciaResponseDto buscarPorId(Long idExperiencia) {
         Experiencia experiencia = experienciaRepository.findById(idExperiencia).orElseThrow(() ->
            new ExperienciaNotFoundException(
                "Experiencia no encontrada con id: " + idExperiencia
            )
        );

        return mapToResponseDto(experiencia);
    }

    @Override
    @Transactional
    public ExperienciaResponseDto actualizar(Long idExperiencia, ExperienciaRequestDto datos) {
         Experiencia experiencia = experienciaRepository.findById(idExperiencia).orElseThrow(() ->
            new ExperienciaNotFoundException(
                "Experiencia no encontrada con id: " + idExperiencia
            )
        );

        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException(
                "El nombre de empresa o proyecto es obligatorio"
            );
        }

        if (datos.puesto() == null || datos.puesto().isBlank()) {
            throw new DatosInvalidosException("El puesto es obligatorio");
        }

        if (datos.fechaInicio() == null) {
            throw new DatosInvalidosException(
                "La fecha de inicio es obligatoria"
            );
        }

        experiencia.setNombre(datos.nombre());
        experiencia.setPuesto(datos.puesto());
        experiencia.setDescripcion(datos.descripcion());
        experiencia.setFechaInicio(datos.fechaInicio());
        experiencia.setFechaFin(datos.fechaFin());

        Experiencia actualizada = experienciaRepository.save(experiencia);

        return mapToResponseDto(actualizada);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long idExperiencia) {
        Experiencia experiencia = experienciaRepository.findById(idExperiencia).orElseThrow(() ->
        new ExperienciaNotFoundException(
            "Experiencia no encontrada con id: " + idExperiencia)
        );

        experienciaRepository.delete(experiencia);
    }

    private ExperienciaResponseDto mapToResponseDto(Experiencia experiencia) {
        return new ExperienciaResponseDto(
            experiencia.getIdExperiencia(),
            experiencia.getNombre(),
            experiencia.getPuesto(),
            experiencia.getDescripcion(),
            experiencia.getFechaInicio(),
            experiencia.getFechaFin(),
            experiencia.getCv().getIdCv()
        );
    }
}
