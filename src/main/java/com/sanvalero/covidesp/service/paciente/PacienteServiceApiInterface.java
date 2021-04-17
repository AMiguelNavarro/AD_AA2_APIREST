package com.sanvalero.covidesp.service.paciente;

import com.sanvalero.covidesp.domain.Paciente;
import com.sanvalero.covidesp.domain.dto.PacienteDTO;

import java.util.List;

public interface PacienteServiceApiInterface {

    List<Paciente> findAll();
    Paciente addNew(PacienteDTO pacienteDTO);
    Paciente modifyPaciente(long id, PacienteDTO pacienteDTO);
    void deletePaciente(long id);
    List<Paciente> findByPositivoCovid(boolean positivoCovid);
}
