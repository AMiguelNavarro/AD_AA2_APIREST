package com.sanvalero.covidesp.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hospitales")
public class Hospital {

    @Schema(description = "Identificador del hospital", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del hospital", example = "Miguel Servet")
    @Column
    private String nombre;

    @Schema(description = "Dispone de planta Covid-19 (true, false)", example = "true")
    @Column
    private boolean plantaCovid;

    @Schema(description = "Número total de ingresos", example = "150")
    @Column
    private int numeroIngresadosTotal;

    @Schema(description = "Dosis administradas de la vacuna", example = "100")
    @Column
    private int dosisVacunaAdministradas;

    @Schema(description = "Porcentaje de camas ocupadas", example = "14.2")
    @Column
    private float porcentajeCamasOcupadas;

    @Schema(description = "Fecha de creación del hospital", example = "1987-05-24")
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad; // FK base de datos -- Relacion 1:N

    @JsonBackReference //  Si hay mas de uno en la misma clase hay que ponerle nombre con 'value'
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    private List<Paciente> listaPacientes;


}
