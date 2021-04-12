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
@Entity(name = "vacunas")
public class Vacuna {

    @Schema(description = "Identificador de la Comunidad Autónoma", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre de la vacuna", example = "Astrazeneca")
    @Column
    private String nombre;

    @Schema(description = "Reino Unido", example = "1")
    @Column
    private String paisCreador;

    @Schema(description = "Porcentaje de inmunidad que ofrece la vacuna", example = "75")
    @Column
    private float porcentajeInmunidad;

    @Schema(description = "Fecha de creación de la vacuna", example = "2021-02-06")
    @Column
    private LocalDate fechaCreacion;

    @Schema(description = "Tiene algún efecto secundario (true, false)", example = "true")
    @Column
    private boolean efectosSecundarios;

}
