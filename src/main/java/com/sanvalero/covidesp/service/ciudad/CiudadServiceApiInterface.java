package com.sanvalero.covidesp.service.ciudad;

import com.sanvalero.covidesp.domain.Ciudad;
import com.sanvalero.covidesp.domain.dto.CiudadDTO;

public interface CiudadServiceApiInterface {

    Ciudad addNew(CiudadDTO ciudadDTO);

}
