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

import com.CV.ApiCV.dtos.ExperienciaRequestDto;
import com.CV.ApiCV.dtos.ExperienciaResponseDto;
import com.CV.ApiCV.services.ExperienciaService;

@RestController
@RequestMapping("/api")
public class ExperienciaController {
     private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @PostMapping("/cvs/{idCv}/experiencias")
    public ResponseEntity<ExperienciaResponseDto> crear(
        @PathVariable Long idCv,
        @RequestBody ExperienciaRequestDto datos
    ) {
        ExperienciaResponseDto creada = experienciaService.crear(idCv, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/cvs/{idCv}/experiencias")
    public ResponseEntity<List<ExperienciaResponseDto>> listarPorCv(
        @PathVariable Long idCv
    ) {
        return ResponseEntity.ok(experienciaService.listarPorCv(idCv));
    }

    @GetMapping("/experiencias/{idExperiencia}")
    public ResponseEntity<ExperienciaResponseDto> buscarPorId(
        @PathVariable Long idExperiencia
    ) {
        return ResponseEntity.ok(experienciaService.buscarPorId(idExperiencia));
    }

    @PutMapping("/experiencias/{idExperiencia}")
    public ResponseEntity<ExperienciaResponseDto> actualizar(
        @PathVariable Long idExperiencia,
        @RequestBody ExperienciaRequestDto datos
    ) {
        return ResponseEntity.ok(
            experienciaService.actualizar(idExperiencia, datos)
        );
    }

    @DeleteMapping("/experiencias/{idExperiencia}")
    public ResponseEntity<Void> eliminarPorId(
        @PathVariable Long idExperiencia
    ) {
        experienciaService.eliminarPorId(idExperiencia);
        return ResponseEntity.noContent().build();
    }
}
