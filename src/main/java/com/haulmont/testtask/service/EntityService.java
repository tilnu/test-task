package com.haulmont.testtask.service;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.entity.Prescription;
import com.haulmont.testtask.entity.enums.Priority;
import com.haulmont.testtask.repository.DoctorRepository;
import com.haulmont.testtask.repository.PatientRepository;
import com.haulmont.testtask.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void savePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    public void deletePrescription(Prescription prescription) {
        prescriptionRepository.delete(prescription);
    }

    public List<Prescription> findPrescriptionByDoctor(Doctor doctor) {
        return prescriptionRepository.findByDoctor(doctor);
    }

    public List<Prescription> findPrescriptionByPatient(Patient patient) {
        return prescriptionRepository.findByPatient(patient);
    }

    public List<Prescription> findPrescriptionByPriority(Priority priority) {
        return prescriptionRepository.findByPriority(priority);
    }
}
