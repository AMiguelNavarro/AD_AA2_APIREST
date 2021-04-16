package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Vacuna;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacunaRepository extends CrudRepository<Vacuna, Long> {

    List<Vacuna> findAll();
    Optional<Vacuna> findByNombre(String nombreVacuna);

}
