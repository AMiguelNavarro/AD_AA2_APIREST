package com.sanvalero.covidesp.controller.errors.ciudad;

public class CiudadNotFoundException extends RuntimeException{

    public CiudadNotFoundException() {
        super();
    }

    public CiudadNotFoundException(String message) {
        super(message);
    }

    public CiudadNotFoundException(long id) {
        super("Ciudad no encontrada: " + id);
    }

}
