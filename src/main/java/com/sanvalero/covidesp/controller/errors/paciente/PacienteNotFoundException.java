package com.sanvalero.covidesp.controller.errors.paciente;

import com.sanvalero.covidesp.controller.errors.ciudad.CiudadNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacienteNotFoundException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(PacienteNotFoundException.class);

    public PacienteNotFoundException() {
        super();
    }

    public PacienteNotFoundException(String message) {
        super(message);
    }

    public PacienteNotFoundException(long id) {
        super("Paciente no encontrado: " + id);
    }

}
