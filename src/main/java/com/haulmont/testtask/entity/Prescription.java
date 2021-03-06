package com.haulmont.testtask.entity;

import com.haulmont.testtask.entity.enums.Priority;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "t_prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(nullable = false)
    private String description;
    @NonNull
    @ManyToOne(optional = false)
    private Doctor doctor;
    @NonNull
    @ManyToOne(optional = false)
    private Patient patient;
    @NonNull
    @Column(nullable = false)
    private LocalDate issueDate;
    @NonNull
    @Column(nullable = false)
    private LocalDate validDate;
    @NonNull
    @Column(nullable = false)
    private Priority priority;

    public Prescription() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public void setValidDate(LocalDate validity) {
        this.validDate = validity;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(validDate, that.validDate) &&
                priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, doctor, patient, issueDate, validDate, priority);
    }
}
