package com.sanvalero.covidesp.service.ciudad;

import com.sanvalero.covidesp.controller.errors.ciudad.CiudadNotFoundException;
import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;
import com.sanvalero.covidesp.repository.CCAARepository;
import com.sanvalero.covidesp.repository.CiudadRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServiceImp implements CiudadServiceApiInterface{


    @Autowired
    private CCAARepository ccaaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;


    @Override
    public Ciudad addNew(CiudadDTO ciudadDTO) {
        val comunidadSeleccionada = ccaaRepository.findByNombre(ciudadDTO.getNombreComunidad());

        if (comunidadSeleccionada == null) {
            System.out.println("no se encuentra la ccaa");
            return null;
        }

        Ciudad ciudad = new Ciudad();

        ciudad.setNombre(ciudadDTO.getNombre());
        ciudad.setExtension(ciudadDTO.getExtension());
        ciudad.setDosisRecibidas(ciudadDTO.getDosisRecibidas());
        ciudad.setNumeroHabitantes(ciudadDTO.getNumeroHabitantes());
        ciudad.setCasosTotales(ciudadDTO.getCasosTotales());
        ciudad.setPlanDeVacunacion(ciudadDTO.isPlanDeVacunacion());
        ciudad.setFechaPrimerPositivo(ciudadDTO.getFechaPrimerPositivo());
        ciudad.setComunidadAutonoma(comunidadSeleccionada);

        return ciudadRepository.save(ciudad);
    }

    @Override
    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    @Override
    public List<Ciudad> findFiltradas(int numeroHabitantes, int dosisVacunaAdministradas, boolean planDeVacunacion) {
        return ciudadRepository.findFiltradas(numeroHabitantes, dosisVacunaAdministradas, planDeVacunacion);
    }

    @Override
    public Ciudad modifyAll(long id, CiudadDTO nuevaCiudadDTO) {

        val ciudadSeleccionada = ciudadRepository.findById(id)
                .orElseThrow(() -> new CiudadNotFoundException(id));

        val comprobarCCAA = ccaaRepository.findByNombre(nuevaCiudadDTO.getNombreComunidad());

        if (comprobarCCAA == null) {
            System.out.println("La CCAA no existe");
            return null;
        }

        Ciudad nuevaCiudad = new Ciudad();

        nuevaCiudad.setId(ciudadSeleccionada.getId());
        nuevaCiudad.setNombre(nuevaCiudadDTO.getNombre());
        nuevaCiudad.setExtension(nuevaCiudadDTO.getExtension());
        nuevaCiudad.setNumeroHabitantes(nuevaCiudadDTO.getNumeroHabitantes());
        nuevaCiudad.setPlanDeVacunacion(nuevaCiudadDTO.isPlanDeVacunacion());
        nuevaCiudad.setFechaPrimerPositivo(nuevaCiudadDTO.getFechaPrimerPositivo());
        nuevaCiudad.setCasosTotales(nuevaCiudadDTO.getCasosTotales());
        nuevaCiudad.setComunidadAutonoma(comprobarCCAA);

        return ciudadRepository.save(nuevaCiudad);
    }

    @Override
    public void deleteOne(long id) {
        val ciudadEliminar = ciudadRepository.findById(id)
                .orElseThrow(() -> new CiudadNotFoundException(id));

        ciudadRepository.delete(ciudadEliminar);
    }

    @Override
    public Ciudad modifyCasosTotales(long id, int casosTotales) {
        val ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new CiudadNotFoundException(id));
        ciudad.setCasosTotales(casosTotales);
        return ciudadRepository.save(ciudad);
    }

}
