package com.sanvalero.covidesp.controller;


import com.sanvalero.covidesp.controller.errors.Response;
import com.sanvalero.covidesp.controller.errors.ccaa.CCAANotFoundException;
import com.sanvalero.covidesp.controller.errors.ciudad.CiudadNotFoundException;
import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;
import com.sanvalero.covidesp.service.ciudad.CiudadServiceApiInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sanvalero.covidesp.controller.errors.Response.NOT_FOUND;

@RestController
@Tag(name = "Ciudades", description = "Listado de ciudades ")
public class CiudadController {

    @Autowired
    private CiudadServiceApiInterface ciudadServiceApiInterface;


    /*-------- LISTAR TODAS LAS CIUDADES */
    @Operation(summary = "Obtiene un listado con todas las ciudades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Listado de ciudades", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ciudad.class))))
    })
    @GetMapping(value = "/ciudades", produces = "application/json")
    public ResponseEntity<List<Ciudad>> getAll() {
        List<Ciudad> listadoCiudades = ciudadServiceApiInterface.findAll();
        return new ResponseEntity<>(listadoCiudades, HttpStatus.OK);
    }

    /*-------- AÑADIR UNA CIUDAD NUEVA */
    @Operation(summary = "Añade una ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se añade correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ciudad.class)))),
            @ApiResponse(responseCode = "409" , description = "La ciudad ya existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PostMapping(value = "/ciudades", produces = "application/json")
    public ResponseEntity<Ciudad> addNew(@RequestBody CiudadDTO ciudadDTO) {
        Ciudad ciudad = ciudadServiceApiInterface.addNew(ciudadDTO);
        return new ResponseEntity<>(ciudad, HttpStatus.OK);
    }


    /*-------- MODIFICAR UNA CIUDAD ENTERA */
    @Operation(summary = "Modifica una ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se modifica correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ciudad.class)))),
            @ApiResponse(responseCode = "404" , description = "La ciudad a eliminar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PutMapping(value = "/ciudades/{id}", produces = "application/json")
    public ResponseEntity<Ciudad> modifyAll(@PathVariable long id, @RequestBody CiudadDTO ciudadDTO) {
        Ciudad nuevaCiudad = ciudadServiceApiInterface.modifyAll(id, ciudadDTO);
        return new ResponseEntity<>(nuevaCiudad, HttpStatus.CREATED);
    }




    @DeleteMapping(value = "/ciudades/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteOne(@PathVariable long id) {
        ciudadServiceApiInterface.deleteOne(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }





    /**
     * Para controlar la excepción NOT_FOUND ERROR CODE 404
     * @param cnfe
     * @return
     */
    @ExceptionHandler(CiudadNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleNotFoundException(CiudadNotFoundException cnfe) {
        Response response = Response.errorResponse(NOT_FOUND, cnfe.getMessage());
//        logger.error(cnfe.getMessage(), cnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
