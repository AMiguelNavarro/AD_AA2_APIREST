package com.sanvalero.covidesp.controller;


import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;
import com.sanvalero.covidesp.service.ciudad.CiudadServiceApiInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Ciudades", description = "Listado de ciudades ")
public class CiudadController {

    @Autowired
    private CiudadServiceApiInterface ciudadServiceApiInterface;


    @PostMapping(value = "/ciudades", produces = "application/json")
    public ResponseEntity<Ciudad> addNew(@RequestBody CiudadDTO ciudadDTO) {
        Ciudad ciudad = ciudadServiceApiInterface.addNew(ciudadDTO);
        return new ResponseEntity<>(ciudad, HttpStatus.OK);
    }

}
