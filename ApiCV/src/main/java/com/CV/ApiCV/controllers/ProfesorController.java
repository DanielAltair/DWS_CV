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

import com.CV.ApiCV.dtos.ProfesorRequestDto;
import com.CV.ApiCV.dtos.ProfesorResponseDto;
import com.CV.ApiCV.services.ProfesorService;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponseDto>> listar() {
        return ResponseEntity.ok(profesorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> buscarPorId(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(profesorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProfesorResponseDto> crear(
        @RequestBody ProfesorRequestDto datos
    ) {
        ProfesorResponseDto creado = profesorService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> actualizar(
        @PathVariable Long id,
        @RequestBody ProfesorRequestDto datos
    ) {
        return ResponseEntity.ok(profesorService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        profesorService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
