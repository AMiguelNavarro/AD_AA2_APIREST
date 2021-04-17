package com.sanvalero.covidesp.service.hospital;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.domain.dto.HospitalDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public interface HospitalServiceApiInterface {

    List<Hospital> findAllHospitales();
    Hospital addNew(HospitalDTO hospitalDTO);
    Hospital modifyAllFromHospital(long id,HospitalDTO hospitalDTO);
    void deleteHospital(long id);
    List<Hospital> findByFechaCreacionAfter(LocalDate fecha);
}
