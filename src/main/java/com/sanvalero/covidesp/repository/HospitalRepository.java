package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    List<Hospital> findAll();

}
