package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.controller.errors.Response;
import com.sanvalero.covidesp.controller.errors.hospital.HospitalNotFoundException;
import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.dto.HospitalDTO;
import com.sanvalero.covidesp.service.hospital.HospitalServiceApiInterface;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Tag(name = "Hospitales", description = "Listado de hospitales")
public class HospitalController {

    @Autowired
    private HospitalServiceApiInterface hospitalServiceApiInterface;

    private final Logger logger = LoggerFactory.getLogger(HospitalController.class);


    /*-------- LISTAR TODOS LOS HOSPITALES */
    @Operation(summary = "Obtiene un listado con todos los hospitales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Listado de hospitales", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hospital.class))))
    })
    @GetMapping(value = "/hospitales", produces = "application/json")
    public ResponseEntity<List<Hospital>> getAll() {
        List<Hospital> listadoHospitales = hospitalServiceApiInterface.findAllHospitales();
        logger.info("Se listan todos los hospitales");
        return new ResponseEntity<>(listadoHospitales, HttpStatus.OK);
    }


    /*-------- AÑADIR UN HOSPITAL NUEVO */
    @Operation(summary = "Añade un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se añade correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hospital.class)))),
            @ApiResponse(responseCode = "409" , description = "El hospital ya existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PostMapping(value = "/hospitales", produces = "application/json")
    public ResponseEntity<Hospital> addNew(@RequestBody HospitalDTO hospitalDTO) {

        val nuevoHospital = hospitalServiceApiInterface.addNew(hospitalDTO);
        logger.info("Se añade un nuevo hospital con ID -> " + nuevoHospital.getId());
        return new ResponseEntity<>(nuevoHospital, HttpStatus.CREATED);
    }


    /*-------- MODIFICAR UN HOSPITAL ENTERO */
    @Operation(summary = "Modifica un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se modifica correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ciudad.class)))),
            @ApiResponse(responseCode = "404" , description = "El hospital a modificar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PutMapping(value = "/hospitales/{id}", produces = "application/json")
    public ResponseEntity<Hospital> modifyAllFromHospital(@PathVariable long id, @RequestBody HospitalDTO hospitalDTO) {
        val hospital = hospitalServiceApiInterface.modifyAllFromHospital(id, hospitalDTO);
        logger.info("Se modifica el hospital con ID -> " + id);
        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }



    /*-------- ELIMINA UN HOSPITAL ENTERO */
    @Operation(summary = "Elimina un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se elimina correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ciudad.class)))),
            @ApiResponse(responseCode = "404" , description = "El hospital a eliminar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @DeleteMapping(value = "/hospitales/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteHospital(@PathVariable long id) {
        hospitalServiceApiInterface.deleteHospital(id);
        logger.info("Se elimina el hospital con ID -> " + id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }




    /*-------- LISTAR TODOS LOS HOSPITALES CON FECHA DE CREACIÓN MAYOR DE LA INTRODUCIDA COMO PARÁMETRO */
    @Operation(summary = "Obtiene un listado con todos los hospitales con fecha de creación mayor que el parámetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Listado de hospitales", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hospital.class))))
    })
    @GetMapping(value = "hospitales/fecha", produces = "application/json")
    public ResponseEntity<List<Hospital>> getHospitalesCreados(@RequestParam(name = "fechaCreacion") String fechaCreacion) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaFormateada = LocalDate.parse(fechaCreacion, formato);
        List<Hospital> listadoHospitales = hospitalServiceApiInterface.findByFechaCreacionAfter(fechaFormateada);
        return new ResponseEntity<>(listadoHospitales, HttpStatus.OK);
    }






    @ExceptionHandler(HospitalNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleNotFoundException(HospitalNotFoundException hnfe) {
        Response response = Response.errorResponse(Response.NOT_FOUND, hnfe.getMessage());
        logger.error(hnfe.getMessage(), hnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
