package com.CV.ApiCV.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CV.ApiCV.dtos.CvRequestDto;
import com.CV.ApiCV.dtos.CvResponseDto;
import com.CV.ApiCV.services.CvService;

@RestController
@RequestMapping("/api")
public class CvController {
     private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/alumnos/{idAlumno}/cvs")
    public ResponseEntity<CvResponseDto> crearParaAlumno(
        @PathVariable Long idAlumno,
        @RequestBody CvRequestDto datos
    ) {
        CvResponseDto creado = cvService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PostMapping("/profesores/{idProfesor}/cvs")
    public ResponseEntity<CvResponseDto> crearParaProfesor(
        @PathVariable Long idProfesor,
        @RequestBody CvRequestDto datos
    ) {
        CvResponseDto creado = cvService.crearParaProfesor(idProfesor, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/alumnos/{idAlumno}/cvs")
    public ResponseEntity<List<CvResponseDto>> listarPorAlumno(
        @PathVariable Long idAlumno
    ) {
        return ResponseEntity.ok(cvService.listarPorAlumno(idAlumno));
    }

    @GetMapping("/profesores/{idProfesor}/cvs")
    public ResponseEntity<List<CvResponseDto>> listarPorProfesor(
        @PathVariable Long idProfesor
    ) {
        return ResponseEntity.ok(cvService.listarPorProfesor(idProfesor));
    }

    @GetMapping("/cvs/{idCv}")
    public ResponseEntity<CvResponseDto> buscarPorId(
        @PathVariable Long idCv
    ) {
        return ResponseEntity.ok(cvService.buscarPorId(idCv));
    }

    @PutMapping("/cvs/{idCv}")
    public ResponseEntity<CvResponseDto> actualizar(
        @PathVariable Long idCv,
        @RequestBody CvRequestDto datos
    ) {
        return ResponseEntity.ok(cvService.actualizar(idCv, datos));
    }

    @DeleteMapping("/cvs/{idCv}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long idCv) {
        cvService.eliminarPorId(idCv);
        return ResponseEntity.noContent().build();
    }
}
