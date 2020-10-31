package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.service.EntityService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

@SpringComponent
public class PatientWindow extends AbstractPersonWindow {
    private TextField phone = new TextField("Номер телефона");
    private Patient patient;
    private Binder<Patient> patientBinder = new Binder();

    @Autowired
    public PatientWindow(EntityService entityService) {
        super();
        phone.setRequiredIndicatorVisible(true);
        formLayout.addComponents(phone);
        addBinder();
        confirm.addClickListener(clickEvent -> {
            try {
                patientBinder.writeBean(patient);
                try {
                    entityService.savePatient(patient);
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
        patientBinder.forField(name).asRequired("Введите имя").bind(Patient::getName, Patient::setName);
        patientBinder.forField(surname).asRequired("Введите фамилию").bind(Patient::getSurname, Patient::setSurname);
        patientBinder.forField(patronymic).asRequired("Введите отчество").bind(Patient::getPatronymic, Patient::setPatronymic);
        patientBinder.forField(phone).asRequired("Введите телефон").bind(Patient::getPhone, Patient::setPhone);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        patientBinder.readBean(patient);
    }
}
