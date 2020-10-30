package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.repository.PrescriptionRepository;
import com.vaadin.ui.*;

public class PrescriptionWindow extends Window {
    private VerticalLayout mainLayout = new VerticalLayout();
    private HorizontalLayout content = new HorizontalLayout();
    private VerticalLayout fields = new VerticalLayout();
    private VerticalLayout dates = new VerticalLayout();
    private HorizontalLayout buttons = new HorizontalLayout();
    private TextArea description = new TextArea("Описание");
    private ComboBox<Doctor> doctor = new ComboBox<>("Врач");
    private ComboBox<Patient> patient = new ComboBox<>("Пациент");
    private DateField issue = new DateField("Дата создания");
    private DateField validity = new DateField("Срок действия");
    private Button confirm = new Button("ОК");
    private Button cancel = new Button("Отменить");
    public PrescriptionWindow(PrescriptionRepository prescriptionRepository) {
        fields.addComponents(description, doctor);
        dates.addComponents(issue, validity, patient);
        content.addComponents(fields, dates);
        buttons.addComponents(confirm, cancel);
        cancel.addClickListener(clickEvent -> close());
        mainLayout.addComponents(content, buttons);
        setContent(mainLayout);
        setModal(true);
    }
}
