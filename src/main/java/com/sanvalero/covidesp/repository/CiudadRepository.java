package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Ciudad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

    List<Ciudad> findAll();

}
