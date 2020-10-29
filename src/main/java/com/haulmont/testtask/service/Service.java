package com.haulmont.testtask.service;

import com.haulmont.testtask.repository.DoctorRepository;
import com.haulmont.testtask.repository.PatientRepository;
import com.haulmont.testtask.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PrescriptionRepository prescriptionRepository;
}
