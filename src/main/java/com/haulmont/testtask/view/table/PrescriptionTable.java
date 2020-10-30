package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.entity.Prescription;
import com.haulmont.testtask.entity.enums.Priority;
import com.haulmont.testtask.repository.DoctorRepository;
import com.haulmont.testtask.repository.PatientRepository;
import com.haulmont.testtask.repository.PrescriptionRepository;
import com.haulmont.testtask.view.window.PrescriptionWindow;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class PrescriptionTable extends VerticalLayout {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PrescriptionRepository prescriptionRepository;
    private Grid<Prescription> prescriptionGrid = new Grid<>();
    private HorizontalLayout toolbar = new HorizontalLayout();
    private HorizontalLayout filter = new HorizontalLayout();
    private ComboBox<Patient> filterDoc = new ComboBox<>("Пациент");
    private ComboBox<Priority> priority = new ComboBox<>("Приоритет");
    private TextField description = new TextField("Описание");
    private Button confirmFilter = new Button("Применить");
    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Изменить");
    private Button deleteButton = new Button("Удалить");

    public PrescriptionTable() {
        buildToolbar();
        buildFilter();
        buildGrid();
    }
    private void buildToolbar() {
        addButton.addClickListener(clickEvent -> getUI().addWindow(new PrescriptionWindow(prescriptionRepository)));
        toolbar.addComponents(addButton, editButton, deleteButton);
        addComponent(toolbar);
    }
    private void buildFilter() {
        filter.addComponents(filterDoc, priority, description, confirmFilter);
        addComponent(filter);
    }
    private void buildGrid() {
        prescriptionGrid.addColumn(Prescription::getDescription).setCaption("Описание");
        prescriptionGrid.addColumn(Prescription::getDoctor).setCaption("Врач");
        prescriptionGrid.addColumn(Prescription::getPatient).setCaption("Пациент");
        prescriptionGrid.addColumn(Prescription::getIssueDate).setCaption("Дата создания");
        prescriptionGrid.addColumn(Prescription::getValidity).setCaption("Срок действия");
        prescriptionGrid.addColumn(Prescription::getPriority).setCaption("Приоритет");
        prescriptionGrid.setSizeFull();
        addComponent(prescriptionGrid);
    }
}
