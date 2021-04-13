package com.sanvalero.covidesp.controller.errors.ccaa;

public class CCAANotFoundException extends RuntimeException{

    public CCAANotFoundException() {
        super();
    }

    public CCAANotFoundException(String message) {
        super(message);
    }

    public CCAANotFoundException(long id) {
        super("CCAA no encontrada: " + id);
    }

}
