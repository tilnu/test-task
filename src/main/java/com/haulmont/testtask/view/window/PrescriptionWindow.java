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

@SpringComponent
public class PrescriptionWindow extends AbstractWindow {
    private final EntityService entityService;
    private final Binder<Prescription> binder = new Binder<>();
    private final TextArea description = new TextArea("Описание");
    private final ComboBox<Priority> priority = new ComboBox<>("Приоритет");
    private final ComboBox<Doctor> doctor = new ComboBox<>("Врач");
    private final ComboBox<Patient> patient = new ComboBox<>("Пациент");
    private final DateField issueDate = new DateField("Дата создания");
    private final DateField validDate = new DateField("Срок действия");
    private Prescription prescription;

    @Autowired
    public PrescriptionWindow(EntityService entityService) {
        this.entityService = entityService;
        description.setRequiredIndicatorVisible(true);
        priority.setRequiredIndicatorVisible(true);
        doctor.setRequiredIndicatorVisible(true);
        patient.setRequiredIndicatorVisible(true);
        issueDate.setRequiredIndicatorVisible(true);
        validDate.setRequiredIndicatorVisible(true);

        priority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);

        VerticalLayout leftPart = new VerticalLayout();
        leftPart.addComponents(issueDate, doctor, patient, priority);

        VerticalLayout rightPart = new VerticalLayout();
        rightPart.addComponents(validDate, description);

        HorizontalLayout content = new HorizontalLayout();
        content.addComponents(leftPart, rightPart);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addComponents(content, buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);

        setContent(mainLayout);
        setResizable(false);
        setModal(true);
        addBinders();

        confirm.addClickListener(clickEvent -> {
            try {
                binder.writeBean(prescription);
                if (validDate.getValue().isAfter(issueDate.getValue())) {
                    try {
                        entityService.savePrescription(prescription);
                        close();
                    } catch (Exception e) {
                        Notification.show("Некорректные данные");
                    }
                }
                else {
                    Notification.show("Срок действия не может быть раньше даты выдачи");
                }
            } catch (ValidationException e) {
                Notification.show("Введите корректные данные");
            }
        });
    }
    private void addBinders() {
        binder.forField(description).asRequired("Введите описание")
                .bind(Prescription::getDescription, Prescription::setDescription);
        binder.forField(doctor).asRequired("Выберите врача")
                .bind(Prescription::getDoctor, Prescription::setDoctor);
        binder.forField(patient).asRequired("Выберите пациента")
                .bind(Prescription::getPatient, Prescription::setPatient);
        binder.forField(priority).asRequired("Выберите приоритет")
                .bind(Prescription::getPriority, Prescription::setPriority);
        binder.forField(issueDate).asRequired("Введите дату выдачи")
                .bind(Prescription::getIssueDate, Prescription::setIssueDate);
        binder.forField(validDate).asRequired("Введите срок действия")
                .bind(Prescription::getValidDate, Prescription::setValidDate);
    }
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
        binder.readBean(prescription);
        doctor.setItems(entityService.getAllDoctors());
        patient.setItems(entityService.getAllPatients());
    }
}
