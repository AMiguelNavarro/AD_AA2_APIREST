package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Tag(name = "CCAA - Comunidades Autónomas", description = "Listado de CCCA - Comunidades Autónomas")
public class CCAAController {


    @Operation(summary = "Obtiene un listado con todas las Comunidades Autónomas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Listado de Comunidades Autónomas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComunidadAutonoma.class))))
    })
    @GetMapping(value = "/ccaa")
    public ResponseEntity<List<ComunidadAutonoma>> getAllComunidades() {
        List<ComunidadAutonoma> listado = null;
        return new ResponseEntity<>(listado, HttpStatus.OK);
    }

}
