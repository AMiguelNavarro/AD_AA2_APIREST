package com.sanvalero.covidesp.service.hospital;

import com.sanvalero.covidesp.controller.errors.ciudad.CiudadNotFoundException;
import com.sanvalero.covidesp.controller.errors.hospital.HospitalNotFoundException;
import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.dto.HospitalDTO;
import com.sanvalero.covidesp.repository.CiudadRepository;
import com.sanvalero.covidesp.repository.HospitalRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImp implements HospitalServiceApiInterface{

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<Hospital> findAllHospitales() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital addNew(HospitalDTO hospitalDTO) {
        val ciudadSeleccionada = ciudadRepository.findByNombre(hospitalDTO.getNombreCiudad())
                .orElseThrow(CiudadNotFoundException::new);

        Hospital nuevoHospital = new Hospital();

        nuevoHospital.setNombre(hospitalDTO.getNombre());
        nuevoHospital.setPlantaCovid(hospitalDTO.isPlantaCovid());
        nuevoHospital.setNumeroIngresadosTotal(hospitalDTO.getNumeroIngresadosTotal());
        nuevoHospital.setDosisVacunaAdministradas(hospitalDTO.getDosisVacunaAdministradas());
        nuevoHospital.setPorcentajeCamasOcupadas(hospitalDTO.getPorcentajeCamasOcupadas());
        nuevoHospital.setFechaCreacion(hospitalDTO.getFechaCreacion());
        nuevoHospital.setCiudad(ciudadSeleccionada);

        return hospitalRepository.save(nuevoHospital);
    }

    @Override
    public Hospital modifyAllFromHospital(long id,HospitalDTO hospitalDTO) {
        val hospitalModificar = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));

        val ciudadSeleccionada = ciudadRepository.findByNombre(hospitalDTO.getNombreCiudad())
                .orElseThrow(CiudadNotFoundException::new);

        hospitalModificar.setNombre(hospitalDTO.getNombre());
        hospitalModificar.setPlantaCovid(hospitalDTO.isPlantaCovid());
        hospitalModificar.setNumeroIngresadosTotal(hospitalDTO.getNumeroIngresadosTotal());
        hospitalModificar.setDosisVacunaAdministradas(hospitalDTO.getDosisVacunaAdministradas());
        hospitalModificar.setPorcentajeCamasOcupadas(hospitalDTO.getPorcentajeCamasOcupadas());
        hospitalModificar.setFechaCreacion(hospitalDTO.getFechaCreacion());
        hospitalModificar.setCiudad(ciudadSeleccionada);

        return hospitalRepository.save(hospitalModificar);
    }

    @Override
    public void deleteHospital(long id) {
        val hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));

        hospitalRepository.delete(hospital);
    }


}
