package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.entity.Prescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    @Override
    List<Prescription> findAll();
    List<Prescription> findByDoctor(Doctor doctor);
}
