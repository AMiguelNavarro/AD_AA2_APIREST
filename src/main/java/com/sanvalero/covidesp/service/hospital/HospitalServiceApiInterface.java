package com.sanvalero.covidesp.service.hospital;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.Hospital;

import java.util.List;

public interface HospitalServiceApiInterface {

    List<Hospital> findAllHospitales();

}
