package com.sanvalero.covidesp.service.ccaa;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.repository.CCAARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CCAAServiceImp implements CCAAServiceApiInterface{

    // Con esto sabe que este objeto es el único para toda la app
    // Hace que se conecte a la base de datos, no hace falta que construllas tu toda la conexión con connect etcetera
    @Autowired
    private CCAARepository ccaaRepository;

    @Override
    public List<ComunidadAutonoma> findAllCCAA() {
        return ccaaRepository.findAll();
    }

    @Override
    public List<ComunidadAutonoma> findByNombre() {
        return null;
    }
}
