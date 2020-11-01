package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.entity.Prescription;
import com.haulmont.testtask.entity.enums.Priority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    @Override
    List<Prescription> findAll();

    List<Prescription> findByDoctor(Doctor doctor);

    List<Prescription> findByPatient(Patient patient);

    List<Prescription> findByPriority(Priority priority);

    List<Prescription> findByDescriptionIgnoreCaseContaining(String description);
}
