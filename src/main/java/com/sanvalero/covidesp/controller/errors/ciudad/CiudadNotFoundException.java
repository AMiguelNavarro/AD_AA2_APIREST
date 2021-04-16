package com.sanvalero.covidesp.controller.errors.ciudad;

import com.sanvalero.covidesp.controller.errors.ccaa.CCAANotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CiudadNotFoundException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(CiudadNotFoundException.class);

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
