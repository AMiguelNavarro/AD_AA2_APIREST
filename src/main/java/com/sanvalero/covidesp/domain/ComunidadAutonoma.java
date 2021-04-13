package com.sanvalero.covidesp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor // Sin constructor vacio Spring siempre da fallo, lo necesita para la BD y el CRUD repository
@Entity(name = "comunidadesAutonomas")
public class ComunidadAutonoma {

    @Schema(description = "Identificador de la Comunidad Autónoma", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre de la Comunidad Autónoma", example = "Aragón")
    @Column
    private String nombre;

    @Schema(description = "Número de ciudades de la Comunidad Autónoma", example = "3")
    @Column
    private int numeroCiudades;

    @Schema(description = "Número de casos totales de Covid-19", example = "25000")
    @Column
    private int casosTotales;

    @Schema(description = "Hay algún positivo en las últimas 24 horas (true, false)", example = "true")
    @Column
    private boolean positivoUltimas24Horas;

    @Schema(description = "Porcentaje de incidencia acumulada", example = "7.57")
    @Column
    private float porcentajeIncidenciaAcumulada;


    @Schema(description = "Fecha del primer positivo en Covid-19", example = "2019-11-27")
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaPrimerPositivo;


    @OneToMany(mappedBy = "comunidadAutonoma") // Se debe poner el nombre de comunidad que se ha dado en ciudad
    private List<Ciudad> listadoCiudades;

}
