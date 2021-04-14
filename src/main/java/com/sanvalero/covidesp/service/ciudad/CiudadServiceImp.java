package com.sanvalero.covidesp.service.ciudad;

import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;
import com.sanvalero.covidesp.repository.CCAARepository;
import com.sanvalero.covidesp.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadServiceImp implements CiudadServiceApiInterface{


    @Autowired
    private CCAARepository ccaaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;



    @Override
    public Ciudad addNew(CiudadDTO ciudadDTO) {
        ComunidadAutonoma comunidadSeleccionada = ccaaRepository.findByNombre(ciudadDTO.getNombreComunidad());

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

}
