package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Override
    List<Doctor> findAll();
}
