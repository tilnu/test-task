package com.haulmont.testtask.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "t_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private String surname;
    @NotBlank
    @Column(nullable = false)
    private String patronymic;
    @NotBlank
    @Digits(fraction = 0, integer = 13)
    @Column(nullable = false)
    private String phone;

    public Patient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return surname + ' ' + name + ' ' + patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(name, patient.name) &&
                Objects.equals(surname, patient.surname) &&
                Objects.equals(patronymic, patient.patronymic) &&
                Objects.equals(phone, patient.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, phone);
    }
}
