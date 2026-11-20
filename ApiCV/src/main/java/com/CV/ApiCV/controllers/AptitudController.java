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

import com.CV.ApiCV.dtos.AptitudRequestDto;
import com.CV.ApiCV.dtos.AptitudResponseDto;
import com.CV.ApiCV.services.AptitudService;

@RestController
@RequestMapping("/api")
public class AptitudController {
    private final AptitudService aptitudService;

    public AptitudController(AptitudService aptitudService) {
        this.aptitudService = aptitudService;
    }

    @PostMapping("/cvs/{idCv}/aptitudes")
    public ResponseEntity<AptitudResponseDto> crear(
        @PathVariable Long idCv,
        @RequestBody AptitudRequestDto datos
    ) {
        AptitudResponseDto creada = aptitudService.crear(idCv, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/cvs/{idCv}/aptitudes")
    public ResponseEntity<List<AptitudResponseDto>> listarPorCv(
        @PathVariable Long idCv
    ) {
        return ResponseEntity.ok(aptitudService.listarPorCv(idCv));
    }

    @GetMapping("/aptitudes/{idAptitud}")
    public ResponseEntity<AptitudResponseDto> buscarPorId(
        @PathVariable Long idAptitud
    ) {
        return ResponseEntity.ok(aptitudService.buscarPorId(idAptitud));
    }

    @PutMapping("/aptitudes/{idAptitud}")
    public ResponseEntity<AptitudResponseDto> actualizar(
        @PathVariable Long idAptitud,
        @RequestBody AptitudRequestDto datos
    ) {
        return ResponseEntity.ok(
            aptitudService.actualizar(idAptitud, datos)
        );
    }

    @DeleteMapping("/aptitudes/{idAptitud}")
    public ResponseEntity<Void> eliminarPorId(
        @PathVariable Long idAptitud
    ) {
        aptitudService.eliminarPorId(idAptitud);
        return ResponseEntity.noContent().build();
    }
}
