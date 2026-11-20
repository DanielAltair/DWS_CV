package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.AptitudRequestDto;
import com.CV.ApiCV.dtos.AptitudResponseDto;
import com.CV.ApiCV.entities.Aptitud;
import com.CV.ApiCV.entities.Cv;
import com.CV.ApiCV.exceptions.AptitudNotFoundException;
import com.CV.ApiCV.exceptions.CvNotFoundException;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.repositories.AptitudRepository;
import com.CV.ApiCV.repositories.CvRepository;
import com.CV.ApiCV.services.AptitudService;

@Service 
public class AptitudServiceImpl implements AptitudService{
    private final AptitudRepository aptitudRepository;
    private final CvRepository cvRepository;

    public AptitudServiceImpl(
        AptitudRepository aptitudRepository,
        CvRepository cvRepository
    ) {
        this.aptitudRepository = aptitudRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public AptitudResponseDto crear(Long idCv, AptitudRequestDto datos) {
        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException("El nombre de la aptitud es obligatorio");
        }

        Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        Aptitud aptitud = new Aptitud();
        aptitud.setNombre(datos.nombre());
        aptitud.setCv(cv);

        Aptitud guardada = aptitudRepository.save(aptitud);

        return mapToResponseDto(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AptitudResponseDto> listarPorCv(Long idCv) {
        if (!cvRepository.existsById(idCv)) {
            throw new CvNotFoundException("CV no encontrado con id: " + idCv);
        }

        List<Aptitud> aptitudes = aptitudRepository.findByCv_IdCv(idCv);

        return aptitudes.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AptitudResponseDto buscarPorId(Long idAptitud) {
         Aptitud aptitud = aptitudRepository.findById(idAptitud).orElseThrow(() ->
            new AptitudNotFoundException(
                "Aptitud no encontrada con id: " + idAptitud
            )
        );

        return mapToResponseDto(aptitud);
    }

    @Override
    @Transactional
    public AptitudResponseDto actualizar(Long idAptitud, AptitudRequestDto datos) {
        Aptitud aptitud = aptitudRepository.findById(idAptitud).orElseThrow(() ->
            new AptitudNotFoundException(
                "Aptitud no encontrada con id: " + idAptitud
            )
        );

        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException(
                "El nombre de la aptitud es obligatorio"
            );
        }

        aptitud.setNombre(datos.nombre());

        Aptitud actualizada = aptitudRepository.save(aptitud);

        return mapToResponseDto(actualizada);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long idAptitud) {
        Aptitud aptitud = aptitudRepository.findById(idAptitud).orElseThrow(() ->
            new AptitudNotFoundException(
                "Aptitud no encontrada con id: " + idAptitud
            )
        );

        aptitudRepository.delete(aptitud);
    }

    private AptitudResponseDto mapToResponseDto(Aptitud aptitud) {
        return new AptitudResponseDto(
            aptitud.getIdAptitud(),
            aptitud.getNombre(),
            aptitud.getCv().getIdCv()
        );
    }
}
