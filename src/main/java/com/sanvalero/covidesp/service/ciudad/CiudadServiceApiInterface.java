package com.sanvalero.covidesp.service.ciudad;

import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;

import java.util.List;

public interface CiudadServiceApiInterface {

    Ciudad addNew(CiudadDTO ciudadDTO);
    List<Ciudad> findAll();
    Ciudad modifyAll(long id, CiudadDTO nuevaCiudadDTO);
    void deleteOne(long id);

}
