package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Override
    List<Patient> findAll();
}
