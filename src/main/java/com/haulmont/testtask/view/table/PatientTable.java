package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.service.EntityService;
import com.haulmont.testtask.view.window.PatientWindow;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class PatientTable extends AbstractTable {
    private final PatientWindow patientWindow;
    private final EntityService entityService;
    private final Grid<Patient> patientGrid = new Grid<>();

    @Autowired
    public PatientTable(EntityService entityService, PatientWindow patientWindow) {
        this.entityService = entityService;
        this.patientWindow = patientWindow;
        patientWindow.addCloseListener(closeEvent -> update());
        buildToolbar();
        buildGrid();
        update();
    }
    private void buildToolbar() {
        addButton.addClickListener(clickEvent -> {
            patientWindow.setPatient(new Patient());
            UI.getCurrent().addWindow(patientWindow);
        });
        editButton.addClickListener(clickEvent -> {
            patientWindow.setPatient(patientGrid.asSingleSelect().getValue());
            UI.getCurrent().addWindow(patientWindow);
        });
        deleteButton.addClickListener(clickEvent -> {
            try {
                entityService.deletePatient(patientGrid.asSingleSelect().getValue());
                update();
            }
            catch (Exception e) {
                Notification.show("Для данного пациента есть рецепты");
            }
        });
        addComponent(toolbar);
    }
    private void buildGrid() {
        patientGrid.addColumn(Patient::getSurname).setCaption("Фамилия");
        patientGrid.addColumn(Patient::getName).setCaption("Имя");
        patientGrid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        patientGrid.addColumn(Patient::getPhone).setCaption("Номер телефона");
        patientGrid.setSizeFull();
        addComponent(patientGrid);

        patientGrid.asSingleSelect().addValueChangeListener(event -> {
            if (patientGrid.asSingleSelect().isEmpty()) {
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
            else {
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });
    }
    private void update() {
        patientGrid.setItems(entityService.getAllPatients());
    }
}
