package com.sanvalero.covidesp.controller.errors.hospital;

public class HospitalNotFoundException extends RuntimeException{

    public HospitalNotFoundException() {
        super();
    }

    public HospitalNotFoundException(String message) {
        super(message);
    }

    public HospitalNotFoundException(long id) {
        super("Hospital no encontrado: " + id);
    }

}
