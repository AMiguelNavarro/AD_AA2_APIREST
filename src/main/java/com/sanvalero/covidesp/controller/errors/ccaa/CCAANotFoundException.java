package com.sanvalero.covidesp.controller.errors.ccaa;

import com.sanvalero.covidesp.controller.CCAAController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCAANotFoundException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(CCAANotFoundException.class);

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
