package com.sanvalero.covidesp.controller.errors;

/*También necesitaremos implementar la clase Response que usamos como respuesta genérica cuando lo que hay que responder
 no es información sino que es la confirmación de que una operación se ha ejecutado correctamente o bien un error porque
  algo no ha ocurrido como se esperaba.*/

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {

    public static final int NO_ERROR = 0;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;

    public static final String MESSAGE = "Operación realizada con éxito";

    private Error error;

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Error {
        private long errorCode;
        private String message;
    }

    public static Response noErrorResponse() {
        return new Response(new Error(NO_ERROR, MESSAGE));
    }

    public static Response errorResponse(int errorCode, String errorMessage) {
        return new Response(new Error(errorCode, errorMessage));
    }
}
