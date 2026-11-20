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

import com.CV.ApiCV.dtos.IdiomaRequestDto;
import com.CV.ApiCV.dtos.IdiomaResponseDto;
import com.CV.ApiCV.services.IdiomaService;

@RestController
@RequestMapping("/api")
public class IdiomaController {
      private final IdiomaService idiomaService;

    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @PostMapping("/cvs/{idCv}/idiomas")
    public ResponseEntity<IdiomaResponseDto> crear(
        @PathVariable Long idCv,
        @RequestBody IdiomaRequestDto datos
    ) {
        IdiomaResponseDto creado = idiomaService.crear(idCv, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/cvs/{idCv}/idiomas")
    public ResponseEntity<List<IdiomaResponseDto>> listarPorCv(
        @PathVariable Long idCv
    ) {
        return ResponseEntity.ok(idiomaService.listarPorCv(idCv));
    }

    @GetMapping("/idiomas/{idIdioma}")
    public ResponseEntity<IdiomaResponseDto> buscarPorId(
        @PathVariable Long idIdioma
    ) {
        return ResponseEntity.ok(idiomaService.buscarPorId(idIdioma));
    }

    @PutMapping("/idiomas/{idIdioma}")
    public ResponseEntity<IdiomaResponseDto> actualizar(
        @PathVariable Long idIdioma,
        @RequestBody IdiomaRequestDto datos
    ) {
        return ResponseEntity.ok(idiomaService.actualizar(idIdioma, datos));
    }

    @DeleteMapping("/idiomas/{idIdioma}")
    public ResponseEntity<Void> eliminarPorId(
        @PathVariable Long idIdioma
    ) {
        idiomaService.eliminarPorId(idIdioma);
        return ResponseEntity.noContent().build();
    }
}
