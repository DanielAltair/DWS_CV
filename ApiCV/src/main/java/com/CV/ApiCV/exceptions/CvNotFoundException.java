package com.CV.ApiCV.exceptions;

public class CvNotFoundException extends RuntimeException {
     public CvNotFoundException(String mensaje) {
        super(mensaje);
    }
}
