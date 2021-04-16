package com.sanvalero.covidesp.controller.errors.hospital;

import com.sanvalero.covidesp.controller.errors.ciudad.CiudadNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HospitalNotFoundException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(HospitalNotFoundException.class);

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
