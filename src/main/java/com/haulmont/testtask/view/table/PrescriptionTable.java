package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.entity.Prescription;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class PrescriptionTable extends VerticalLayout {
    Grid<Prescription> prescriptionGrid = new Grid<>();
    HorizontalLayout toolbar = new HorizontalLayout();
    Button addButton;
    Button editButton;
    Button deleteButton;

    public PrescriptionTable() {
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
        prescriptionGrid.addColumn(Prescription::getDescription).setCaption("Description");
        prescriptionGrid.addColumn(Prescription::getDoctor).setCaption("Doctor");
        prescriptionGrid.addColumn(Prescription::getPatient).setCaption("Patient");
        prescriptionGrid.addColumn(Prescription::getIssueDate).setCaption("Issue Date");
        prescriptionGrid.addColumn(Prescription::getValidity).setCaption("Validity");
        prescriptionGrid.addColumn(Prescription::getPriority).setCaption("Priority");
        prescriptionGrid.setSizeFull();
        addComponent(prescriptionGrid);
    }
}
