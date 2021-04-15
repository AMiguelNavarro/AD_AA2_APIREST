package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Ciudad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

    List<Ciudad> findAll();
    Optional<Ciudad> findByNombre(String nombreCiudad);

}
