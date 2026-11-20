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

import com.CV.ApiCV.dtos.CompetenciaRequestDto;
import com.CV.ApiCV.dtos.CompetenciaResponseDto;
import com.CV.ApiCV.services.CompetenciaService;

@RestController
@RequestMapping("/api")
public class CompetenciaController {
      private final CompetenciaService competenciaService;

    public CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @PostMapping("/cvs/{idCv}/competencias")
    public ResponseEntity<CompetenciaResponseDto> crear(
        @PathVariable Long idCv,
        @RequestBody CompetenciaRequestDto datos
    ) {
        CompetenciaResponseDto creada = competenciaService.crear(idCv, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/cvs/{idCv}/competencias")
    public ResponseEntity<List<CompetenciaResponseDto>> listarPorCv(
        @PathVariable Long idCv
    ) {
        return ResponseEntity.ok(competenciaService.listarPorCv(idCv));
    }

    @GetMapping("/competencias/{idCompetencia}")
    public ResponseEntity<CompetenciaResponseDto> buscarPorId(
        @PathVariable Long idCompetencia
    ) {
        return ResponseEntity.ok(competenciaService.buscarPorId(idCompetencia));
    }

    @PutMapping("/competencias/{idCompetencia}")
    public ResponseEntity<CompetenciaResponseDto> actualizar(
        @PathVariable Long idCompetencia,
        @RequestBody CompetenciaRequestDto datos
    ) {
        return ResponseEntity.ok(
            competenciaService.actualizar(idCompetencia, datos)
        );
    }

    @DeleteMapping("/competencias/{idCompetencia}")
    public ResponseEntity<Void> eliminarPorId(
        @PathVariable Long idCompetencia
    ) {
        competenciaService.eliminarPorId(idCompetencia);
        return ResponseEntity.noContent().build();
    }
}
