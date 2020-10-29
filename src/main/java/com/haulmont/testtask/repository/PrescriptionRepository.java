package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    @Override
    List<Prescription> findAll();
    List<Prescription> findByDoctor();
}
