package com.sanvalero.covidesp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comunidadesAutonomas")
public class ComunidadAutonoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private int numeroCiudades;
    @Column
    private int casosTotales;
    @Column
    private boolean positivoUltimas24Horas;
    @Column
    private float porcentajeIncidenciaAcumulada;
    @Column
    private LocalDate fechaPrimerCasoCovid;

}
