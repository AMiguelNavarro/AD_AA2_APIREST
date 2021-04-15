package com.sanvalero.covidesp.service.hospital;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.dto.HospitalDTO;

import java.util.List;

public interface HospitalServiceApiInterface {

    List<Hospital> findAllHospitales();
    Hospital addNew(HospitalDTO hospitalDTO);

}
