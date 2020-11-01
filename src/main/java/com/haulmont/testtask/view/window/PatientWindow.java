package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.service.EntityService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class PatientWindow extends AbstractPersonWindow {
    private final TextField phone = new TextField("Номер телефона");
    private final Binder<Patient> binder = new Binder();
    private Patient patient;

    @Autowired
    public PatientWindow(EntityService entityService) {
        phone.setRequiredIndicatorVisible(true);
        formLayout.addComponents(phone);
        addBinder();
        confirm.addClickListener(clickEvent -> {
            try {
                binder.writeBean(patient);
                try {
                    entityService.savePatient(patient);
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
                .bind(Patient::getName, Patient::setName);
        binder.forField(surname).asRequired("Введите фамилию")
                .bind(Patient::getSurname, Patient::setSurname);
        binder.forField(patronymic).asRequired("Введите отчество")
                .bind(Patient::getPatronymic, Patient::setPatronymic);
        binder.forField(phone).asRequired("Введите телефон")
                .bind(Patient::getPhone, Patient::setPhone);
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
        binder.readBean(patient);
    }
}
