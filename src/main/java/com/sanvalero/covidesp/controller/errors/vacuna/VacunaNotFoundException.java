package com.sanvalero.covidesp.controller.errors.vacuna;

public class VacunaNotFoundException extends  RuntimeException{

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
