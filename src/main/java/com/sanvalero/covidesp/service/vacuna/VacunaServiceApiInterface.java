package com.sanvalero.covidesp.service.vacuna;

import com.sanvalero.covidesp.domain.Vacuna;

import java.util.List;
import java.util.Optional;

public interface VacunaServiceApiInterface {

    List<Vacuna> findAll();
    Vacuna addNew(Vacuna vacuna);
    Vacuna modifyVacuna(long id, Vacuna vacuna);
    void deleteVacuna(long id);
    List<Vacuna> findByPorcentajeInmunidadGreaterThan(float porcentajeInmunidad);
}
