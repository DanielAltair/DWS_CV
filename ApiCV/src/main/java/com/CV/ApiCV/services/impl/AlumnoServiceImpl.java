package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.AlumnoRequestDto;
import com.CV.ApiCV.dtos.AlumnoResponseDto;
import com.CV.ApiCV.entities.Alumno;
import com.CV.ApiCV.exceptions.AlumnoNotFoundException;
import com.CV.ApiCV.exceptions.DatosInvalidosException;
import com.CV.ApiCV.repositories.AlumnoRepository;
import com.CV.ApiCV.services.AlumnoService;

@Service 
public class AlumnoServiceImpl implements AlumnoService{
     private final AlumnoRepository alumnoRepository;

    

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional 
    public AlumnoResponseDto crear(AlumnoRequestDto datos) {

        if (datos.nombreCompleto() == null || datos.nombreCompleto().isBlank()) {
            throw new DatosInvalidosException("El nombre completo es obligatorio");}

        Alumno alumno = new Alumno();

        alumno.setNombreCompleto(datos.nombreCompleto());
        alumno.setEmail(datos.email());
        alumno.setTelefono(datos.telefono());
        alumno.setCiudad(datos.ciudad());

        Alumno guardado = alumnoRepository.save(alumno);

        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional (readOnly = true)
    public List<AlumnoResponseDto> listar() {
        List<Alumno> alumnos = alumnoRepository.findAll();

        return alumnos.stream()
            .map(this::mapToResponseDto)
            .toList();
    }

    @Override
    @Transactional (readOnly = true)
    public AlumnoResponseDto buscarPorId(Long id) {
         Alumno alumno = alumnoRepository.findById(id).orElseThrow(() ->
            new AlumnoNotFoundException("Alumno no encontrado con id: " + id)
        );

        return mapToResponseDto(alumno);
    }

    @Override
    @Transactional 
    public AlumnoResponseDto actualizar(Long id, AlumnoRequestDto datos) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(() ->
            new AlumnoNotFoundException("Alumno no encontrado con id: " + id)
        );

        if (datos.nombreCompleto() == null || datos.nombreCompleto().isBlank()) {
            throw new DatosInvalidosException("El nombre completo es obligatorio");
        }

        alumno.setNombreCompleto(datos.nombreCompleto());
        alumno.setEmail(datos.email());
        alumno.setTelefono(datos.telefono());
        alumno.setCiudad(datos.ciudad());

        Alumno actualizado = alumnoRepository.save(alumno);

        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional 
    public void eliminarPorId(Long id) {
          Alumno alumno = alumnoRepository.findById(id).orElseThrow(() ->
            new AlumnoNotFoundException("Alumno no encontrado con id: " + id)
        );

        alumnoRepository.delete(alumno);
    }

    private AlumnoResponseDto mapToResponseDto(Alumno alumno) {
        return new AlumnoResponseDto(
            alumno.getIdAlumno(),
            alumno.getNombreCompleto(),
            alumno.getEmail(),
            alumno.getTelefono(),
            alumno.getCiudad()
        );
    }
}

