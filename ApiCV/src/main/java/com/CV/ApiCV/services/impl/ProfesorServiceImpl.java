package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.ProfesorRequestDto;
import com.CV.ApiCV.dtos.ProfesorResponseDto;
import com.CV.ApiCV.entities.Profesor;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.exceptions.ProfesorNotFoundException;
import com.CV.ApiCV.repositories.ProfesorRepository;
import com.CV.ApiCV.services.ProfesorService;

@Service 
public class ProfesorServiceImpl implements ProfesorService{
     private final ProfesorRepository profesorRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    @Transactional 
    public ProfesorResponseDto crear(ProfesorRequestDto datos) {
        if (datos.nombreCompleto() == null || datos.nombreCompleto().isBlank()) {
        throw new DatosInvalidosException("El nombre completo es obligatorio");
        }

        Profesor profesor = new Profesor();
        profesor.setNombreCompleto(datos.nombreCompleto());
        profesor.setEmail(datos.email());
        profesor.setTelefono(datos.telefono());
        profesor.setCiudad(datos.ciudad());

        Profesor guardado = profesorRepository.save(profesor);

        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional (readOnly = true)
    public List<ProfesorResponseDto> listar() {
        List<Profesor> profesores = profesorRepository.findAll();

        return profesores.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional (readOnly = true)
    public ProfesorResponseDto buscarPorId(Long id) {
         Profesor profesor = profesorRepository.findById(id).orElseThrow(() ->
            new ProfesorNotFoundException("Profesor no encontrado con id: " + id)
        );

        return mapToResponseDto(profesor);
    }

    @Override
    @Transactional 
    public ProfesorResponseDto actualizar(Long id, ProfesorRequestDto datos) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() ->
            new ProfesorNotFoundException("Profesor no encontrado con id: " + id)
        );

        if (datos.nombreCompleto() == null || datos.nombreCompleto().isBlank()) {
            throw new DatosInvalidosException("El nombre completo es obligatorio");
        }

        profesor.setNombreCompleto(datos.nombreCompleto());
        profesor.setEmail(datos.email());
        profesor.setTelefono(datos.telefono());
        profesor.setCiudad(datos.ciudad());

        Profesor actualizado = profesorRepository.save(profesor);

        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional 
    public void eliminarPorId(Long id) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() ->
            new ProfesorNotFoundException("Profesor no encontrado con id: " + id)
        );

        profesorRepository.delete(profesor);
    }

    private ProfesorResponseDto mapToResponseDto(Profesor profesor) {
        return new ProfesorResponseDto(
            profesor.getIdProfesor(),
            profesor.getNombreCompleto(),
            profesor.getEmail(),
            profesor.getTelefono(),
            profesor.getCiudad()
        );
    }
}
