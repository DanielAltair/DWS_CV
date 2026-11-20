package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.FormacionRequestDto;
import com.CV.ApiCV.dtos.FormacionResponseDto;
import com.CV.ApiCV.entities.Cv;
import com.CV.ApiCV.entities.Formacion;
import com.CV.ApiCV.exceptions.CvNotFoundException;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.exceptions.FormacionNotFoundException;
import com.CV.ApiCV.repositories.CvRepository;
import com.CV.ApiCV.repositories.FormacionRepository;
import com.CV.ApiCV.services.FormacionService;

@Service 
public class FormacionServiceImpl implements FormacionService{
     private final FormacionRepository formacionRepository;
    private final CvRepository cvRepository;

    public FormacionServiceImpl(
        FormacionRepository formacionRepository,
        CvRepository cvRepository
    ) {
        this.formacionRepository = formacionRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional 
    public FormacionResponseDto crear(Long idCv, FormacionRequestDto datos) {
        if (datos.titulo() == null || datos.titulo().isBlank()) {
        throw new DatosInvalidosException("El título es obligatorio");
        }

        if (datos.centro() == null || datos.centro().isBlank()) {
        throw new DatosInvalidosException("El centro es obligatorio");
        }

        if (datos.fechaInicio() == null) {
            throw new DatosInvalidosException("La fecha de inicio es obligatoria");
        }

        Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        Formacion formacion = new Formacion();

        formacion.setTitulo(datos.titulo());
        formacion.setCentro(datos.centro());
        formacion.setFechaInicio(datos.fechaInicio());
        formacion.setFechaFin(datos.fechaFin());
        formacion.setCv(cv);

        Formacion guardada = formacionRepository.save(formacion);

        return mapToResponseDto(guardada);
    }

    @Override
    @Transactional (readOnly = true)
    public List<FormacionResponseDto> listarPorCv(Long idCv) {
         if (!cvRepository.existsById(idCv)) {
        throw new CvNotFoundException("CV no encontrado con id: " + idCv);
        }

        List<Formacion> formaciones = formacionRepository.findByCv_IdCv(idCv);

        return formaciones.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional (readOnly = true)
    public FormacionResponseDto buscarPorId(Long idFormacion) {
        Formacion formacion = formacionRepository.findById(idFormacion).orElseThrow(() ->
            new FormacionNotFoundException(
                "Formación no encontrada con id: " + idFormacion
            )
        );

        return mapToResponseDto(formacion);
    }

    @Override
    @Transactional 
    public FormacionResponseDto actualizar(Long idFormacion, FormacionRequestDto datos) {
        Formacion formacion = formacionRepository.findById(idFormacion).orElseThrow(() ->
            new FormacionNotFoundException(
                "Formación no encontrada con id: " + idFormacion
            )
        );

        if (datos.titulo() == null || datos.titulo().isBlank()) {
            throw new DatosInvalidosException("El título es obligatorio");
        }

        if (datos.centro() == null || datos.centro().isBlank()) {
            throw new DatosInvalidosException("El centro es obligatorio");
        }

        if (datos.fechaInicio() == null) {
            throw new DatosInvalidosException("La fecha de inicio es obligatoria");
        }

        formacion.setTitulo(datos.titulo());
        formacion.setCentro(datos.centro());
        formacion.setFechaInicio(datos.fechaInicio());
        formacion.setFechaFin(datos.fechaFin());

        Formacion actualizada = formacionRepository.save(formacion);

        return mapToResponseDto(actualizada);
    }

    @Override
    @Transactional 
    public void eliminarPorId(Long idFormacion) {
         Formacion formacion = formacionRepository.findById(idFormacion).orElseThrow(() ->
            new FormacionNotFoundException(
                "Formación no encontrada con id: " + idFormacion
            )
        );

        formacionRepository.delete(formacion);
    }

    private FormacionResponseDto mapToResponseDto(Formacion formacion) {
        return new FormacionResponseDto(
            formacion.getIdFormacion(),
            formacion.getTitulo(),
            formacion.getCentro(),
            formacion.getFechaInicio(),
            formacion.getFechaFin(),
            formacion.getCv().getIdCv()
        );
    }
}
