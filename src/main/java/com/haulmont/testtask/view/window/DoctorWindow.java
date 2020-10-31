package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.service.EntityService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

@SpringComponent
public class DoctorWindow extends AbstractPersonWindow {
    private TextField specialisation = new TextField("Специализация");
    private Doctor doctor;
    private Binder<Doctor> doctorBinder = new Binder();

    @Autowired
    public DoctorWindow(EntityService entityService) {
        super();
        specialisation.setRequiredIndicatorVisible(true);
        formLayout.addComponent(specialisation);
        addBinder();
        confirm.addClickListener(clickEvent -> {
                try {
                    doctorBinder.writeBean(doctor);
                    try {
                        entityService.saveDoctor(doctor);
                        close();
                    }
                    catch (ConstraintViolationException e) {
                        Notification.show("Некорректные данные");
                    }
                }
                catch (ValidationException e) {
                    Notification.show("Введите корректные данные");
                }
                });
    }
    private void addBinder() {
        doctorBinder.forField(name).asRequired("Введите имя").bind(Doctor::getName, Doctor::setName);
        doctorBinder.forField(surname).asRequired("Введите фамилию").bind(Doctor::getSurname, Doctor::setSurname);
        doctorBinder.forField(patronymic).asRequired("Введите отчество").bind(Doctor::getPatronymic, Doctor::setPatronymic);
        doctorBinder.forField(specialisation).asRequired("Введите специализацию").bind(Doctor::getSpecialisation, Doctor::setSpecialisation);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctorBinder.readBean(doctor);
    }
}
