package com.sanvalero.covidesp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class HospitalDTO {

    private String nombre;
    private boolean plantaCovid;
    private int numeroIngresadosTotal;
    private int dosisVacunaAdministradas;
    private float porcentajeCamasOcupadas;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;
    private String nombreCiudad;

}
