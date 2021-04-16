package com.sanvalero.covidesp.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pacientes")
public class Paciente {

    @Schema(description = "Identificador de la Comunidad Autónoma", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del paciente", example = "Pepe")
    @Column
    private String nombre;

    @Schema(description = "Edad del paciente", example = "57")
    @Column
    private int edad;

    @Schema(description = "Paciente positivo en Covid (true, false)", example = "true")
    @Column
    private boolean positivoCovid;

    @Schema(description = "Peso del paciente", example = "75.5")
    @Column
    private float peso;

    @Schema(description = "Fecha de ingreso en el hospital", example = "2020-04-02")
    @Column
    private LocalDate fechaIngreso;


    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; // FK base de datos -- Relacion 1:N

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "vacuna_id")
    private Vacuna vacuna; // FK base de datos -- Relacion 1:N

}
