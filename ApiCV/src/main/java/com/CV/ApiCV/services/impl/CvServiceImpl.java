package com.CV.ApiCV.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CV.ApiCV.dtos.AptitudResponseDto;
import com.CV.ApiCV.dtos.CompetenciaResponseDto;
import com.CV.ApiCV.dtos.CvRequestDto;
import com.CV.ApiCV.dtos.CvResponseDto;
import com.CV.ApiCV.dtos.ExperienciaResponseDto;
import com.CV.ApiCV.dtos.FormacionResponseDto;
import com.CV.ApiCV.dtos.IdiomaResponseDto;
import com.CV.ApiCV.entities.Alumno;
import com.CV.ApiCV.entities.Cv;
import com.CV.ApiCV.entities.Profesor;
import com.CV.ApiCV.exceptions.AlumnoNotFoundException;
import com.CV.ApiCV.exceptions.CvNotFoundException;
import com.CV.ApiCV.exceptions.ProfesorNotFoundException;
import com.CV.ApiCV.repositories.AlumnoRepository;
import com.CV.ApiCV.repositories.CvRepository;
import com.CV.ApiCV.repositories.ProfesorRepository;
import com.CV.ApiCV.services.CvService;

@Service 
public class CvServiceImpl implements CvService {
    
    private final CvRepository cvRepository;
    private final AlumnoRepository alumnoRepository;
    private final ProfesorRepository profesorRepository;

    public CvServiceImpl(
        CvRepository cvRepository,
        AlumnoRepository alumnoRepository,
        ProfesorRepository profesorRepository
    ) {
        this.cvRepository = cvRepository;
        this.alumnoRepository = alumnoRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    @Transactional 
    public CvResponseDto crearParaAlumno(Long idAlumno, CvRequestDto datos) {
         Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(() ->
            new AlumnoNotFoundException("Alumno no encontrado con id: " + idAlumno)
        );

        Cv cv = new Cv();
        cv.setPerfil(datos.perfil());
        cv.setAlumno(alumno);

        Cv guardado = cvRepository.save(cv);

        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional 
    public CvResponseDto crearParaProfesor(Long idProfesor, CvRequestDto datos) {
        Profesor profesor = profesorRepository.findById(idProfesor).orElseThrow(() ->
            new ProfesorNotFoundException("Profesor no encontrado con id: " + idProfesor)
        );

        Cv cv = new Cv();
        cv.setPerfil(datos.perfil());
        cv.setProfesor(profesor);

        Cv guardado = cvRepository.save(cv);

        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional (readOnly = true)
    public List<CvResponseDto> listarPorAlumno(Long idAlumno) {
        if (!alumnoRepository.existsById(idAlumno)) {
        throw new AlumnoNotFoundException(
            "Alumno no encontrado con id: " + idAlumno
        );
        }

        List<Cv> cvs = cvRepository.findByAlumno_IdAlumno(idAlumno);

        return cvs.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional (readOnly = true)
    public List<CvResponseDto> listarPorProfesor(Long idProfesor) {
         if (!profesorRepository.existsById(idProfesor)) {
            throw new ProfesorNotFoundException(
            "Profesor no encontrado con id: " + idProfesor
        );
    }

        List<Cv> cvs = cvRepository.findByProfesor_IdProfesor(idProfesor);

        return cvs.stream()
        .map(this::mapToResponseDto)
        .toList();
    }

    @Override
    @Transactional (readOnly = true)
    public CvResponseDto buscarPorId(Long idCv) {
         Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        return mapToResponseDto(cv);
    }

    @Override
    @Transactional 
    public CvResponseDto actualizar(Long idCv, CvRequestDto datos) {
        Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        cv.setPerfil(datos.perfil());

        Cv actualizado = cvRepository.save(cv);

        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional 
    public void eliminarPorId(Long idCv) {
         Cv cv = cvRepository.findById(idCv).orElseThrow(() ->
            new CvNotFoundException("CV no encontrado con id: " + idCv)
        );

        cvRepository.delete(cv);
    }

    private CvResponseDto mapToResponseDto(Cv cv) {
        Long idAlumno = null;
        Long idProfesor = null;

        if (cv.getAlumno() != null) {
            idAlumno = cv.getAlumno().getIdAlumno();
        }

        if (cv.getProfesor() != null) {
            idProfesor = cv.getProfesor().getIdProfesor();
        }

        List<FormacionResponseDto> formaciones = cv.getFormaciones().stream()
            .map(formacion -> new FormacionResponseDto(
                formacion.getIdFormacion(),
                formacion.getTitulo(),
                formacion.getCentro(),
                formacion.getFechaInicio(),
                formacion.getFechaFin(),
                cv.getIdCv()
            ))
            .toList();

        List<ExperienciaResponseDto> experiencias = cv.getExperiencias().stream()
            .map(experiencia -> new ExperienciaResponseDto(
                experiencia.getIdExperiencia(),
                experiencia.getNombre(),
                experiencia.getPuesto(),
                experiencia.getDescripcion(),
                experiencia.getFechaInicio(),
                experiencia.getFechaFin(),
                cv.getIdCv()
            ))
            .toList();

        List<CompetenciaResponseDto> competencias = cv.getCompetencias().stream()
            .map(competencia -> new CompetenciaResponseDto(
                competencia.getIdCompetencia(),
                competencia.getNombre(),
                cv.getIdCv()
            ))
            .toList();

        List<AptitudResponseDto> aptitudes = cv.getAptitudes().stream()
            .map(aptitud -> new AptitudResponseDto(
                aptitud.getIdAptitud(),
                aptitud.getNombre(),
                cv.getIdCv()
            ))
            .toList();

        List<IdiomaResponseDto> idiomas = cv.getIdiomas().stream()
            .map(idioma -> new IdiomaResponseDto(
                idioma.getIdIdioma(),
                idioma.getNombre(),
                idioma.getNivel(),
                cv.getIdCv()
            ))
            .toList();

        return new CvResponseDto(
            cv.getIdCv(),
            cv.getPerfil(),
            idAlumno,
            idProfesor,
            formaciones,
            experiencias,
            competencias,
            aptitudes,
            idiomas
        );
    }
}
