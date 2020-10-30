package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.repository.PatientRepository;
import com.haulmont.testtask.view.window.PatientWindow;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class PatientTable extends VerticalLayout {
    PatientRepository patientRepository;
    Grid<Patient> patientGrid = new Grid<>();
    HorizontalLayout toolbar = new HorizontalLayout();
    Button addButton = new Button("Добавить");
    Button editButton = new Button("Изменить");
    Button deleteButton = new Button("Удалить");

    @Autowired
    public PatientTable(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        buildToolbar();
        buildGrid();
        update();
    }
    void buildToolbar() {
        addButton.addClickListener(clickEvent -> getUI().addWindow(new PatientWindow()));
        toolbar.addComponents(addButton, editButton, deleteButton);
        addComponent(toolbar);
    }
    void buildGrid() {
        patientGrid.addColumn(Patient::getSurname).setCaption("Фамилия");
        patientGrid.addColumn(Patient::getName).setCaption("Имя");
        patientGrid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        patientGrid.addColumn(Patient::getPhone).setCaption("Номер телефона");
        patientGrid.setSizeFull();
        addComponent(patientGrid);
    }
    void update() {
        patientGrid.setItems(patientRepository.findAll());
    }
}
