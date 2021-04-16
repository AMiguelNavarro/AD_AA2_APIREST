package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    List<Hospital> findAll();
    Optional<Hospital> findByNombre(String nombreHospital);
}
