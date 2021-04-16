package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.controller.errors.Response;
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


    /*-------- LISTAR TODOS LAS VACUNAS */
    @Operation(summary = "Lista todos las vacunas")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Se listan correctamente las vacunas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacuna.class))))
    })
    @GetMapping(value = "/vacunas", produces = "application/json")
    public ResponseEntity<List<Vacuna>> getAll() {
        List<Vacuna> listaVacunas = vacunaServiceApiInterface.findAll();
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
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


}
