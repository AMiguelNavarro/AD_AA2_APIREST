package com.sanvalero.covidesp.controller.errors.paciente;

public class PacienteNotFoundException extends RuntimeException{

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
