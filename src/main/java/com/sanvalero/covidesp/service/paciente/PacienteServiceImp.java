package com.sanvalero.covidesp.service.paciente;

import com.sanvalero.covidesp.controller.errors.hospital.HospitalNotFoundException;
import com.sanvalero.covidesp.controller.errors.paciente.PacienteNotFoundException;
import com.sanvalero.covidesp.controller.errors.vacuna.VacunaNotFoundException;
import com.sanvalero.covidesp.domain.Paciente;
import com.sanvalero.covidesp.domain.Vacuna;
import com.sanvalero.covidesp.domain.dto.PacienteDTO;
import com.sanvalero.covidesp.repository.HospitalRepository;
import com.sanvalero.covidesp.repository.PacienteRepository;
import com.sanvalero.covidesp.repository.VacunaRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImp implements PacienteServiceApiInterface{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private VacunaRepository vacunaRepository;



    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente addNew(PacienteDTO pacienteDTO) {

        val hospitalSeleccionado = hospitalRepository.findByNombre(pacienteDTO.getNombreHospital())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital no encontrado: " + pacienteDTO.getNombreHospital()));

        Vacuna vacunaSeleccionada;

        if (pacienteDTO.getNombreVacuna() != null) {
            vacunaSeleccionada = vacunaRepository.findByNombre(pacienteDTO.getNombreVacuna())
                    .orElseThrow(() -> new VacunaNotFoundException("Vacuna no encontrada: " + pacienteDTO.getNombreVacuna()));
        } else {
            vacunaSeleccionada = null;
        }

        val nuevoPaciente = new Paciente();

        nuevoPaciente.setNombre(pacienteDTO.getNombre());
        nuevoPaciente.setEdad(pacienteDTO.getEdad());
        nuevoPaciente.setPositivoCovid(pacienteDTO.isPositivoCovid());
        nuevoPaciente.setPeso(pacienteDTO.getPeso());
        nuevoPaciente.setFechaIngreso(pacienteDTO.getFechaIngreso());
        nuevoPaciente.setHospital(hospitalSeleccionado);
        nuevoPaciente.setVacuna(vacunaSeleccionada);

        return pacienteRepository.save(nuevoPaciente);
    }

    @Override
    public Paciente modifyPaciente(long id, PacienteDTO pacienteDTO) {

        val pacienteSeleccionado = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException(id));

        System.out.println(pacienteDTO.getNombreHospital());

        val hospitalSeleccionado = hospitalRepository.findByNombre(pacienteDTO.getNombreHospital())
                .orElseThrow(() -> new HospitalNotFoundException("El hospital " + pacienteDTO.getNombreHospital() + " no existe"));

        Vacuna vacunaSeleccionada;

        if (pacienteDTO.getNombreVacuna() != null) {
            vacunaSeleccionada = vacunaRepository.findByNombre(pacienteDTO.getNombreVacuna())
                    .orElseThrow(() -> new VacunaNotFoundException("La vacuna " + pacienteDTO.getNombreVacuna() + " no existe"));
        } else {
            vacunaSeleccionada = null;
        }

        Paciente pacienteModificado = new Paciente();

        pacienteModificado.setNombre(pacienteDTO.getNombre());
        pacienteModificado.setEdad(pacienteDTO.getEdad());
        pacienteModificado.setPositivoCovid(pacienteDTO.isPositivoCovid());
        pacienteModificado.setFechaIngreso(pacienteDTO.getFechaIngreso());
        pacienteModificado.setHospital(hospitalSeleccionado);
        pacienteModificado.setVacuna(vacunaSeleccionada);

        return pacienteRepository.save(pacienteModificado);
    }

    @Override
    public void deletePaciente(long id) {
        val pacienteSeleccionado = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException(id));

        pacienteRepository.delete(pacienteSeleccionado);
    }
}
