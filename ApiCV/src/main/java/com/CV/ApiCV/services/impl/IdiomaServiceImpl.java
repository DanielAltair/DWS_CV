package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.IdiomaRequestDto;
import com.CV.ApiCV.dtos.IdiomaResponseDto;
import com.CV.ApiCV.entities.Cv;
import com.CV.ApiCV.entities.Idioma;
import com.CV.ApiCV.exceptions.CvNotFoundException;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.exceptions.IdiomaNotFoundException;
import com.CV.ApiCV.repositories.CvRepository;
import com.CV.ApiCV.repositories.IdiomaRepository;
import com.CV.ApiCV.services.IdiomaService;

@Service 
public class IdiomaServiceImpl implements IdiomaService{
    private final IdiomaRepository idiomaRepository;
    private final CvRepository cvRepository;

    public IdiomaServiceImpl(
        IdiomaRepository idiomaRepository,
        CvRepository cvRepository
    ) {
        this.idiomaRepository = idiomaRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public IdiomaResponseDto crear(Long idCv, IdiomaRequestDto datos) {
        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException("El nombre del idioma es obligatorio");
        }

        if (datos.nivel() == null || datos.nivel().isBlank()) {
            throw new DatosInvalidosException("El nivel del idioma es obligatorio");
        }

        Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        Idioma idioma = new Idioma();
        idioma.setNombre(datos.nombre());
        idioma.setNivel(datos.nivel());
        idioma.setCv(cv);

        Idioma guardado = idiomaRepository.save(idioma);

        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdiomaResponseDto> listarPorCv(Long idCv) {
        if (!cvRepository.existsById(idCv)) {
            throw new CvNotFoundException("CV no encontrado con id: " + idCv);
        }

        List<Idioma> idiomas = idiomaRepository.findByCv_IdCv(idCv);

        return idiomas.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public IdiomaResponseDto buscarPorId(Long idIdioma) {
         Idioma idioma = idiomaRepository.findById(idIdioma).orElseThrow(() ->
            new IdiomaNotFoundException(
                "Idioma no encontrado con id: " + idIdioma
            )
        );

        return mapToResponseDto(idioma);
    }

    @Override
    @Transactional
    public IdiomaResponseDto actualizar(Long idIdioma, IdiomaRequestDto datos) {
        Idioma idioma = idiomaRepository.findById(idIdioma).orElseThrow(() ->
            new IdiomaNotFoundException(
                "Idioma no encontrado con id: " + idIdioma
            )
        );

        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new DatosInvalidosException(
                "El nombre del idioma es obligatorio"
            );
        }

        if (datos.nivel() == null || datos.nivel().isBlank()) {
            throw new DatosInvalidosException(
                "El nivel del idioma es obligatorio"
            );
        }

        idioma.setNombre(datos.nombre());
        idioma.setNivel(datos.nivel());

        Idioma actualizado = idiomaRepository.save(idioma);

        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long idIdioma) {
        Idioma idioma = idiomaRepository.findById(idIdioma).orElseThrow(() ->
            new IdiomaNotFoundException(
                "Idioma no encontrado con id: " + idIdioma
            )
        );

        idiomaRepository.delete(idioma);
    }

    private IdiomaResponseDto mapToResponseDto(Idioma idioma) {
        return new IdiomaResponseDto(
            idioma.getIdIdioma(),
            idioma.getNombre(),
            idioma.getNivel(),
            idioma.getCv().getIdCv()
        );
    }
}
