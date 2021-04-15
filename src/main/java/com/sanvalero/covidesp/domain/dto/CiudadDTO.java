package com.sanvalero.covidesp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CiudadDTO {

    private String nombre;
    private float extension;
    private int dosisRecibidas;
    private int numeroHabitantes;
    private int casosTotales;
    private boolean planDeVacunacion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaPrimerPositivo;
    private String nombreComunidad;

}
