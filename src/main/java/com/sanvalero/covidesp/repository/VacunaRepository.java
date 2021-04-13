package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Vacuna;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaRepository extends CrudRepository<Vacuna, Long> {
}