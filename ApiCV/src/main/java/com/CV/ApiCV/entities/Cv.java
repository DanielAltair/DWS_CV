package com.CV.ApiCV.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "cv")
public class Cv {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cv")
    private Long idCv;

    @Column(name = "perfil", length = 1000)
    private String perfil;

    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = true)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = true)
    private Profesor profesor;

    public Cv() {
    }

    public Long getIdCv() {
        return idCv;
    }

    public void setIdCv(Long idCv) {
        this.idCv = idCv;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    
}
