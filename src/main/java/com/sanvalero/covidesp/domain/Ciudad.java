package com.sanvalero.covidesp.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ciudades")
public class Ciudad {

    @Schema(description = "Identificador de la ciudad", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre de la ciudad", example = "Zaragoza")
    @Column
    private String nombre;

    @Schema(description = "Extensión de la ciudad", example = "973.8")
    @Column
    private float extension;

    @Schema(description = "Número de dosis recibidas", example = "50000")
    @Column
    private int dosisRecibidas;

    @Schema(description = "Número de habitantes", example = "666880")
    @Column
    private int numeroHabitantes;

    @Schema(description = "Número de casos totales", example = "25000")
    @Column
    private int casosTotales;

    @Schema(description = "Disponen ya de plan de vacunación (true, false)", example = "true")
    @Column
    private boolean planDeVacunacion;

    @Schema(description = "Fecha del primer positivo en Covid-19", example = "2019-11-27")
    @Column
    private LocalDate fechaPrimerPositivo;

    @ManyToOne
    @JoinColumn(name = "ccaa_id")
    private ComunidadAutonoma comunidadAutonoma; // FK de la base de datos -- Relacion 1:N

}
