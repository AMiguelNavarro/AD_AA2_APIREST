package com.sanvalero.covidesp.controller.errors.vacuna;

import com.sanvalero.covidesp.controller.errors.paciente.PacienteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VacunaNotFoundException extends  RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(VacunaNotFoundException.class);

    public VacunaNotFoundException() {
        super();
    }

    public VacunaNotFoundException(String message) {
        super(message);
    }

    public VacunaNotFoundException(long id) {
        super("Vacuna no encontrada: " + id);
    }

}
