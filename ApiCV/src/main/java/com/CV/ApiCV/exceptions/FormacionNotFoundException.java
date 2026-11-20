package com.CV.ApiCV.exceptions;

public class FormacionNotFoundException extends RuntimeException{
     public FormacionNotFoundException(String mensaje) {
        super(mensaje);
    }
}
