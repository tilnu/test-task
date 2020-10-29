package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Override
    List<Patient> findAll();
}
