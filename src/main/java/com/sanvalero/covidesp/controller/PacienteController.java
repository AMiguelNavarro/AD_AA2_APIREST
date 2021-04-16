package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.controller.errors.Response;
import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.Paciente;
import com.sanvalero.covidesp.domain.dto.PacienteDTO;
import com.sanvalero.covidesp.service.paciente.PacienteServiceApiInterface;
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
@Tag(name = "Pacientes", description = "Listado de pacientes")
public class PacienteController {

    @Autowired
    private PacienteServiceApiInterface pacienteServiceApiInterface;

    /*-------- LISTAR TODOS LOS PACIENTES */
    @Operation(summary = "Lista todos los pacientes")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Se listan correctamente los pacientes", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Paciente.class))))
    })
    @GetMapping(value = "/pacientes", produces = "application/json")
    public ResponseEntity<List<Paciente>> getAll() {
        List<Paciente> listaPacientes = pacienteServiceApiInterface.findAll();
        return new ResponseEntity<>(listaPacientes, HttpStatus.OK);
    }


    /*-------- AÑADIR UN PACIENTE NUEVO */
    @Operation(summary = "Añade un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se añade correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Paciente.class)))),
            @ApiResponse(responseCode = "409" , description = "El paciente ya existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PostMapping(value = "/pacientes", produces = "application/json")
    public ResponseEntity<Paciente> addNew(@RequestBody PacienteDTO pacienteDTO) {

        Paciente nuevoPaciente = pacienteServiceApiInterface.addNew(pacienteDTO);

        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }


    /*-------- MODIFICAR UN PACIENTE ENTERO */
    @Operation(summary = "Modifica un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se modifica correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Paciente.class)))),
            @ApiResponse(responseCode = "404" , description = "El paciente a modificar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PutMapping(value = "/pacientes/{id}", produces = "application/json")
    public ResponseEntity<Paciente> modifyPaciente(@PathVariable long id, @RequestBody PacienteDTO pacienteDTO) {

        val pacienteModificado = pacienteServiceApiInterface.modifyPaciente(id, pacienteDTO);

        return new ResponseEntity<>(pacienteModificado, HttpStatus.CREATED);
    }


    /*-------- ELIMINA UN PACIENTE ENTERO */
    @Operation(summary = "Elimina un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se elimina correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Paciente.class)))),
            @ApiResponse(responseCode = "404" , description = "El paciente a eliminar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @DeleteMapping(value = "/pacientes/{id}", produces = "application/json")
    public ResponseEntity<Response> deletePaciente(@PathVariable long id) {

        pacienteServiceApiInterface.deletePaciente(id);

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

}
