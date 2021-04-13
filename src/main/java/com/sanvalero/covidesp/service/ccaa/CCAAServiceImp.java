package com.sanvalero.covidesp.service.ccaa;

import com.sanvalero.covidesp.controller.errors.ccaa.CCAANotFoundException;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.repository.CCAARepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ComunidadAutonoma> findById(long id) {
        return ccaaRepository.findById(id);
    }

    @Override
    public ComunidadAutonoma addNew(ComunidadAutonoma comunidadAutonoma) {
        return ccaaRepository.save(comunidadAutonoma);
    }

    @Override
    public ComunidadAutonoma modififyCCAA(long id, ComunidadAutonoma nuevaCCAA) {

        val ccaaModify = ccaaRepository.findById(id)
                .orElseThrow(() -> new CCAANotFoundException(id));
        nuevaCCAA.setId(ccaaModify.getId());

        return ccaaRepository.save(nuevaCCAA);
    }

    @Override
    public void deleteCCAA(long id) {
        val ccaaDelete = ccaaRepository.findById(id)
                .orElseThrow(() -> new CCAANotFoundException(id));

        ccaaRepository.delete(ccaaDelete);
    }
}
