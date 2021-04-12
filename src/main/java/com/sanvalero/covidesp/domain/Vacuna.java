package com.sanvalero.covidesp.domain;


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String paisCreador;
    @Column
    private float porcentajeInmunidad;
    @Column
    private LocalDate fechaCreacion;
    @Column
    private boolean efectosSecundarios;

}
