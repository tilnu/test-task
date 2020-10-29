package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Patient;
import com.vaadin.ui.*;

public class PatientTable extends VerticalLayout {
    Grid<Patient> patientGrid = new Grid<>();
    HorizontalLayout toolbar = new HorizontalLayout();
    Button addButton;
    Button editButton;
    Button deleteButton;

    public PatientTable() {
        buildToolbar();
        buildGrid();
    }
    void buildToolbar() {
        addButton = new Button("Create");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        toolbar.addComponents(addButton, editButton, deleteButton);
        addComponent(toolbar);
    }
    void buildGrid() {
        patientGrid.addColumn(Patient::getSurname).setCaption("Surname");
        patientGrid.addColumn(Patient::getName).setCaption("Name");
        patientGrid.addColumn(Patient::getPatronymic).setCaption("Patronymic");
        patientGrid.setSizeFull();
        addComponent(patientGrid);
    }
}
