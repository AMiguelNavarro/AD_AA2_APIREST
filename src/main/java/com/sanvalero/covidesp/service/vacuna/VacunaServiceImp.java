package com.sanvalero.covidesp.service.vacuna;

import com.sanvalero.covidesp.controller.errors.vacuna.VacunaNotFoundException;
import com.sanvalero.covidesp.domain.Vacuna;
import com.sanvalero.covidesp.repository.VacunaRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacunaServiceImp implements VacunaServiceApiInterface{

    @Autowired
    private VacunaRepository vacunaRepository;

    @Override
    public List<Vacuna> findAll() {
        return vacunaRepository.findAll();
    }

    @Override
    public Vacuna addNew(Vacuna vacuna) {
        return vacunaRepository.save(vacuna);
    }

    @Override
    public Vacuna modifyVacuna(long id, Vacuna nuevaVacuna) {

        val vacunaModificar = vacunaRepository.findById(id)
                .orElseThrow(() -> new VacunaNotFoundException(id));

        vacunaModificar.setNombre(nuevaVacuna.getNombre());
        vacunaModificar.setPaisCreador(nuevaVacuna.getPaisCreador());
        vacunaModificar.setPorcentajeInmunidad(nuevaVacuna.getPorcentajeInmunidad());
        vacunaModificar.setFechaCreacion(nuevaVacuna.getFechaCreacion());
        vacunaModificar.setEfectosSecundarios(nuevaVacuna.isEfectosSecundarios());

        return vacunaRepository.save(vacunaModificar);

    }

    @Override
    public void deleteVacuna(long id) {
        val vacunaBorrar = vacunaRepository.findById(id)
                .orElseThrow(() -> new VacunaNotFoundException(id));

        vacunaRepository.delete(vacunaBorrar);
    }
}
