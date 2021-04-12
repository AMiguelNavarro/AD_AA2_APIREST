package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {
}
