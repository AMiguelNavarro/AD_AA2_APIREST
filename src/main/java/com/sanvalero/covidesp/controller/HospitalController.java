package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.service.hospital.HospitalServiceApiInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
