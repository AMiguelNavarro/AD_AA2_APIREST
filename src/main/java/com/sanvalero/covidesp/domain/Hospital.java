package com.sanvalero.covidesp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hospitales")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private boolean plantaCovid;
    @Column
    private int numeroIngresadosTotal;
    @Column
    private int dosisVacunaAdministradas;
    @Column
    private float porcentajeCamasOcupadas;
    @Column
    private LocalDate fechaCreacion;

//    private Ciudad ciudad; // FK base de datos -- Relacion 1:N
//    private Vacuna vacuna; // FK base de datos -- Relacion N:N

}
