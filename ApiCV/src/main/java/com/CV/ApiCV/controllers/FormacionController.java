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

import com.CV.ApiCV.dtos.FormacionRequestDto;
import com.CV.ApiCV.dtos.FormacionResponseDto;
import com.CV.ApiCV.services.FormacionService;

@RestController
@RequestMapping("/api")
public class FormacionController {
    private final FormacionService formacionService;

    public FormacionController(FormacionService formacionService) {
        this.formacionService = formacionService;
    }

    @PostMapping("/cvs/{idCv}/formaciones")
    public ResponseEntity<FormacionResponseDto> crear(
        @PathVariable Long idCv,
        @RequestBody FormacionRequestDto datos
    ) {
        FormacionResponseDto creada = formacionService.crear(idCv, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/cvs/{idCv}/formaciones")
    public ResponseEntity<List<FormacionResponseDto>> listarPorCv(
        @PathVariable Long idCv
    ) {
        return ResponseEntity.ok(formacionService.listarPorCv(idCv));
    }

    @GetMapping("/formaciones/{idFormacion}")
    public ResponseEntity<FormacionResponseDto> buscarPorId(
        @PathVariable Long idFormacion
    ) {
        return ResponseEntity.ok(formacionService.buscarPorId(idFormacion));
    }

    @PutMapping("/formaciones/{idFormacion}")
    public ResponseEntity<FormacionResponseDto> actualizar(
        @PathVariable Long idFormacion,
        @RequestBody FormacionRequestDto datos
    ) {
        return ResponseEntity.ok(formacionService.actualizar(idFormacion, datos));
    }

    @DeleteMapping("/formaciones/{idFormacion}")
    public ResponseEntity<Void> eliminarPorId(
        @PathVariable Long idFormacion
    ) {
        formacionService.eliminarPorId(idFormacion);
        return ResponseEntity.noContent().build();
    }
}
