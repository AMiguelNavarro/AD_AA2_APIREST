package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.controller.errors.Response;
import com.sanvalero.covidesp.controller.errors.hospital.HospitalNotFoundException;
import com.sanvalero.covidesp.controller.errors.vacuna.VacunaNotFoundException;
import com.sanvalero.covidesp.domain.Paciente;
import com.sanvalero.covidesp.domain.Vacuna;
import com.sanvalero.covidesp.service.vacuna.VacunaServiceApiInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Vacunas", description = "Listado de vacunas")
public class VacunaController {

    @Autowired
    private VacunaServiceApiInterface vacunaServiceApiInterface;

    private final Logger logger = LoggerFactory.getLogger(VacunaController.class);


    /*-------- LISTAR TODOS LAS VACUNAS */
    @Operation(summary = "Lista todos las vacunas")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Se listan correctamente las vacunas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacuna.class))))
    })
    @GetMapping(value = "/vacunas", produces = "application/json")
    public ResponseEntity<List<Vacuna>> getAll() {
        List<Vacuna> listaVacunas = vacunaServiceApiInterface.findAll();
        logger.info("Se listan todas las vacunas");
        return new ResponseEntity<>(listaVacunas, HttpStatus.OK);
    }


    /*-------- AÑADIR UNA VACUNA NUEVA */
    @Operation(summary = "Añade una vacuna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se añade correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)))),
            @ApiResponse(responseCode = "409" , description = "La vacuna ya existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PostMapping(value = "/vacunas", produces = "application/json")
    public ResponseEntity<Vacuna> addNew(@RequestBody Vacuna vacuna) {
        val nuevaVacuna = vacunaServiceApiInterface.addNew(vacuna);
        logger.info("Se añade la vacuna con ID -> " + nuevaVacuna.getId());
        return new ResponseEntity<>(nuevaVacuna, HttpStatus.CREATED);
    }


    /*-------- MODIFICAR UNA VACUNA ENTERA */
    @Operation(summary = "Modifica una vacuna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se modifica correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)))),
            @ApiResponse(responseCode = "404" , description = "La vacuna a modificar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PutMapping(value = "/vacunas/{id}", produces = "application/json")
    public ResponseEntity<Vacuna> modifyVacuna(@PathVariable long id, @RequestBody Vacuna vacuna) {
        val vacunaModificar = vacunaServiceApiInterface.modifyVacuna(id, vacuna);
        logger.info("Se modifica la vacuna con ID -> " + id);
        return new ResponseEntity<>(vacunaModificar, HttpStatus.CREATED);
    }


    /*-------- ELIMINA UNA VACUNA ENTERA */
    @Operation(summary = "Elimina una vacuna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se elimina correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)))),
            @ApiResponse(responseCode = "404" , description = "La vacuna a eliminar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @DeleteMapping(value = "/vacunas/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteVacuna(@PathVariable long id) {
        vacunaServiceApiInterface.deleteVacuna(id);
        logger.info("Se elimina la vacuna con ID -> " + id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }



    /*-------- LISTAR TODOS LAS VACUNAS CON PORCENTAJE DE INMUNIDAD MAYOR QUE EL INDICADO */
    @Operation(summary = "Lista todos las vacunas con % de inmunidad mayor que el indicado")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Se listan correctamente las vacunas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacuna.class))))
    })
    @GetMapping(value = "/vacunas/inmunidad", produces = "application/json")
    public ResponseEntity<List<Vacuna>> getVacunasPorcentajeInmunidad(@RequestParam(name = "porcentajeInmunidad") float porcentajeInmunidad) {
        List<Vacuna> listadoVacunas = vacunaServiceApiInterface.findByPorcentajeInmunidadGreaterThan(porcentajeInmunidad);
        return new ResponseEntity<>(listadoVacunas, HttpStatus.OK);
    }



    @Operation(summary = "Modifica el porcentaje de inmunidad de la vacuna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha modificado correctamente",content = @Content(schema = @Schema(implementation = Vacuna.class))),
            @ApiResponse(responseCode = "404", description = "La vacuna no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/vacunas/{id}/cambiar-porcentajeInmunidad", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Vacuna> modifyPorcentajeInmunidad(@PathVariable long id, @RequestBody float porcentajeInmunidad) {
        val vacuna = vacunaServiceApiInterface.modifyPorcentajeInmunidad(id, porcentajeInmunidad);
        logger.info("Se modifica el % de inmunidad de la vacuna con ID -> " + id + " a: " + porcentajeInmunidad);
        return new ResponseEntity<>(vacuna, HttpStatus.OK);
    }





    @ExceptionHandler(VacunaNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleNotFoundException(HospitalNotFoundException hnfe) {
        Response response = Response.errorResponse(Response.NOT_FOUND, hnfe.getMessage());
        logger.error(hnfe.getMessage(), hnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
