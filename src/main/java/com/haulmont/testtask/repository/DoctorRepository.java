package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Override
    List<Doctor> findAll();
}
