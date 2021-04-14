package com.sanvalero.covidesp.service.hospital;

import com.sanvalero.covidesp.domain.Hospital;
import com.sanvalero.covidesp.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImp implements HospitalServiceApiInterface{

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> findAllHospitales() {
        return hospitalRepository.findAll();
    }


}
