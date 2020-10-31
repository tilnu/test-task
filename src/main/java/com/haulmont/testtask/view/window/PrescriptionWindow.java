package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.entity.Prescription;
import com.haulmont.testtask.entity.enums.Priority;
import com.haulmont.testtask.service.EntityService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

@SpringComponent
public class PrescriptionWindow extends Window {
    private EntityService entityService;
    private Prescription prescription = new Prescription();
    private Binder<Prescription> prescriptionBinder = new Binder<>();
    private VerticalLayout leftPart = new VerticalLayout();
    private VerticalLayout rightPart = new VerticalLayout();
    private VerticalLayout mainLayout = new VerticalLayout();
    private HorizontalLayout content = new HorizontalLayout();
    private HorizontalLayout buttons = new HorizontalLayout();
    private TextArea description = new TextArea("Описание");
    private ComboBox<Priority> priority = new ComboBox<>("Приоритет");
    private ComboBox<Doctor> doctor = new ComboBox<>("Врач");
    private ComboBox<Patient> patient = new ComboBox<>("Пациент");
    private DateField issue = new DateField("Дата создания");
    private DateField validity = new DateField("Срок действия");
    private Button confirm = new Button("ОК");
    private Button cancel = new Button("Отменить");

    @Autowired
    public PrescriptionWindow(EntityService entityService) {
        this.entityService = entityService;
        description.setRequiredIndicatorVisible(true);
        priority.setRequiredIndicatorVisible(true);
        doctor.setRequiredIndicatorVisible(true);
        patient.setRequiredIndicatorVisible(true);
        issue.setRequiredIndicatorVisible(true);
        validity.setRequiredIndicatorVisible(true);

        priority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);

        leftPart.addComponents(issue, doctor, patient, priority);
        rightPart.addComponents(validity, description);
        content.addComponents(leftPart, rightPart);

        cancel.addClickListener(clickEvent -> close());
        buttons.addComponents(confirm, cancel);

        mainLayout.addComponents(content, buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);

        setContent(mainLayout);
        setResizable(false);
        setModal(true);
        addBinders();
        confirm.addClickListener(clickEvent -> {
            if (validity.getValue().isAfter(issue.getValue())) {
                try {
                    prescriptionBinder.writeBean(prescription);
                    try {
                        entityService.savePrescription(prescription);
                        close();
                    } catch (ConstraintViolationException e) {
                        Notification.show("Некорректные данные");
                    }
                } catch (ValidationException e) {
                    Notification.show("Введите корректные данные");
                }
            } else {
                Notification.show("Срок действия не может быть раньше даты выдачи");
            }
        });
    }
    private void addBinders() {
        prescriptionBinder.forField(description).asRequired("Введите описание").bind(Prescription::getDescription, Prescription::setDescription);
        prescriptionBinder.forField(doctor).asRequired("Выберите врача").bind(Prescription::getDoctor, Prescription::setDoctor);
        prescriptionBinder.forField(patient).asRequired("Выберите пациента").bind(Prescription::getPatient, Prescription::setPatient);
        prescriptionBinder.forField(priority).asRequired("Выберите приоритет").bind(Prescription::getPriority, Prescription::setPriority);
        prescriptionBinder.forField(issue).asRequired("Введите дату выдачи").bind(Prescription::getIssueDate, Prescription::setIssueDate);
        prescriptionBinder.forField(validity).asRequired("Введите срок действия").bind(Prescription::getValidity, Prescription::setValidity);
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
        prescriptionBinder.readBean(prescription);
        doctor.setItems(entityService.getAllDoctors());
        patient.setItems(entityService.getAllPatients());
    }
}
