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

import com.CV.ApiCV.dtos.AlumnoRequestDto;
import com.CV.ApiCV.dtos.AlumnoResponseDto;
import com.CV.ApiCV.services.AlumnoService;

@RestController 
@RequestMapping("/api/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponseDto>> listar() {
        return ResponseEntity.ok(alumnoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> buscarPorId(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(alumnoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDto> crear(
        @RequestBody AlumnoRequestDto datos
    ) {
        AlumnoResponseDto creado = alumnoService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> actualizar(
        @PathVariable Long id,
        @RequestBody AlumnoRequestDto datos
    ) {
        return ResponseEntity.ok(alumnoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        alumnoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
