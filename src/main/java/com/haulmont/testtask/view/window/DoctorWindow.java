package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.service.EntityService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class DoctorWindow extends AbstractPersonWindow {
    private final TextField specialisation = new TextField("Специализация");
    private final Binder<Doctor> binder = new Binder();
    private Doctor doctor;

    @Autowired
    public DoctorWindow(EntityService entityService) {
        specialisation.setRequiredIndicatorVisible(true);
        formLayout.addComponent(specialisation);
        addBinder();
        confirm.addClickListener(clickEvent -> {
            try {
                binder.writeBean(doctor);
                try {
                    entityService.saveDoctor(doctor);
                    close();
                }
                catch (Exception e) {
                    Notification.show("Некорректные данные");
                }
            }
            catch (ValidationException e) {
                Notification.show("Введите корректные данные");
            }
        });
    }
    private void addBinder() {
        binder.forField(name).asRequired("Введите имя")
                .bind(Doctor::getName, Doctor::setName);
        binder.forField(surname).asRequired("Введите фамилию")
                .bind(Doctor::getSurname, Doctor::setSurname);
        binder.forField(patronymic).asRequired("Введите отчество")
                .bind(Doctor::getPatronymic, Doctor::setPatronymic);
        binder.forField(specialisation).asRequired("Введите специализацию")
                .bind(Doctor::getSpecialisation, Doctor::setSpecialisation);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        binder.readBean(doctor);
    }
}
