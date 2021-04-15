package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.controller.errors.Response;
import com.sanvalero.covidesp.controller.errors.hospital.HospitalNotFoundException;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.dto.HospitalDTO;
import com.sanvalero.covidesp.service.hospital.HospitalServiceApiInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Hospitales", description = "Listado de hospitales")
public class HospitalController {

    @Autowired
    private HospitalServiceApiInterface hospitalServiceApiInterface;

    @GetMapping(value = "/hospitales", produces = "application/json")
    public ResponseEntity<List<Hospital>> getAll() {
        List<Hospital> listadoHospitales = hospitalServiceApiInterface.findAllHospitales();
        return new ResponseEntity<>(listadoHospitales, HttpStatus.OK);
    }

    @PostMapping(value = "/hospitales", produces = "application/json")
    public ResponseEntity<Hospital> addNew(@RequestBody HospitalDTO hospitalDTO) {

        Hospital nuevoHospital = hospitalServiceApiInterface.addNew(hospitalDTO);

        return new ResponseEntity<>(nuevoHospital, HttpStatus.CREATED);
    }

//    @PutMapping(value = "/hospitales/{id}", produces = "application/json")
//    @DeleteMapping(value = "/hospitales/{id}", produces = "application/json")



    @ExceptionHandler(HospitalNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleNotFoundException(HospitalNotFoundException hnfe) {
        Response response = Response.errorResponse(Response.NOT_FOUND, hnfe.getMessage());
//      logger.error(hnfe.getMessage(), hnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
