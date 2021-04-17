package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

    List<Paciente> findAll();
    List<Paciente> findByPositivoCovid(boolean positivo);

}
