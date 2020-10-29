package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.repository.DoctorRepository;
import com.haulmont.testtask.view.window.DoctorWindow;
import com.vaadin.ui.*;

public class DoctorTable extends VerticalLayout {
    DoctorRepository doctorRepository;
    Grid<Doctor> doctorGrid = new Grid<>();
    HorizontalLayout toolbar = new HorizontalLayout();
    Button addButton;
    Button editButton;
    Button deleteButton;
    Button statisticsButton;
    public DoctorTable() {
        buildToolbar();
        buildGrid();
    }
    void buildToolbar() {
        addButton = new Button("Create");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        statisticsButton = new Button("Statistics");
        addButton.addClickListener(clickEvent -> UI.getCurrent().addWindow(new DoctorWindow()));
        toolbar.addComponents(addButton, editButton, deleteButton, statisticsButton);
        addComponent(toolbar);
    }
    void buildGrid() {
        doctorGrid.addColumn(Doctor::getSurname).setCaption("Surname");
        doctorGrid.addColumn(Doctor::getName).setCaption("Name");
        doctorGrid.addColumn(Doctor::getPatronymic).setCaption("Patronymic");
        doctorGrid.addColumn(Doctor::getSpecialisation).setCaption("Specialisation");
        doctorGrid.setSizeFull();
        addComponent(doctorGrid);
    }
}