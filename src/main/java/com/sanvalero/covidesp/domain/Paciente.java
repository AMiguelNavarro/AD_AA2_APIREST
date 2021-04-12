package com.sanvalero.covidesp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private int edad;
    @Column
    private boolean positivoCovid;
    @Column
    private float peso;
    @Column
    private LocalDate fechaIngreso;

//    private Hospital hospital; // FK base de datos -- Relacion 1:N

}
