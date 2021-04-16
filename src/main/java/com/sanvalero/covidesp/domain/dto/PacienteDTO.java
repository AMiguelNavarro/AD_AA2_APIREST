package com.sanvalero.covidesp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PacienteDTO {

    private String nombre;
    private int edad;
    private boolean positivoCovid;
    private float peso;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaIngreso;
    private String nombreHospital;
    private String nombreVacuna;


}
