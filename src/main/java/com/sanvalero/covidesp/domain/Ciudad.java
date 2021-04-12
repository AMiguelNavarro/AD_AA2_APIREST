package com.sanvalero.covidesp.domain;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private float extension;
    @Column
    private int dosisRecibidas;
    @Column
    private int numeroHabitantes;
    @Column
    private int casosTotales;
    @Column
    private boolean planDeVacunacion;

//    private LocalDate fechaPlanVacunacion;
//    private ComunidadAutonoma comunidadAutonoma; // FK de la base de datos -- Relacion 1:N

}
