package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Patient;
import com.haulmont.testtask.entity.Prescription;
import com.haulmont.testtask.entity.enums.Priority;
import com.haulmont.testtask.service.EntityService;
import com.haulmont.testtask.view.window.PrescriptionWindow;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class PrescriptionTable extends VerticalLayout {

    private EntityService entityService;
    private PrescriptionWindow prescriptionWindow;
    private Grid<Prescription> prescriptionGrid = new Grid<>();
    private HorizontalLayout toolbar = new HorizontalLayout();
    private HorizontalLayout filter = new HorizontalLayout();
    private ComboBox<Patient> filterPatient = new ComboBox<>("Пациент");
    private ComboBox<Priority> priority = new ComboBox<>("Приоритет");
    private TextField description = new TextField("Описание");
    private Button confirmFilter = new Button("Применить");
    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Изменить");
    private Button deleteButton = new Button("Удалить");

    @Autowired
    public PrescriptionTable(EntityService entityService, PrescriptionWindow prescriptionWindow) {
        this.entityService = entityService;
        this.prescriptionWindow = prescriptionWindow;
        prescriptionWindow.addCloseListener(closeEvent -> update());
        buildToolbar();
        buildFilter();
        buildGrid();
        update();
    }
    private void buildToolbar() {
        addButton.addClickListener(clickEvent -> {
            prescriptionWindow.setPrescription(new Prescription());
            UI.getCurrent().addWindow(prescriptionWindow);
        });

        editButton.addClickListener(clickEvent -> {
            prescriptionWindow.setPrescription(prescriptionGrid.asSingleSelect().getValue());
            UI.getCurrent().addWindow(prescriptionWindow);
        });

        deleteButton.addClickListener(clickEvent -> {
            entityService.deletePrescription(prescriptionGrid.asSingleSelect().getValue());
            update();
        });

        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        toolbar.addComponents(addButton, editButton, deleteButton);
        addComponent(toolbar);
    }
    private void buildFilter() {
        filterPatient.addFocusListener(focusEvent -> filterPatient.setItems(entityService.getAllPatients()));
        priority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);
        filter.addComponents(filterPatient, priority, description, confirmFilter);
        filter.setComponentAlignment(confirmFilter, Alignment.BOTTOM_CENTER);
        addComponent(filter);

        confirmFilter.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        confirmFilter.addClickListener(clickEvent -> {
            HashSet<Prescription> filterResult = new HashSet<>(entityService.getAllPrescriptions());
            if (!filterPatient.isEmpty()) {
                filterResult.retainAll(entityService.findPrescriptionByPatient(filterPatient.getValue()));
            }
            if (!priority.isEmpty()) {
                filterResult.retainAll(entityService.findPrescriptionByPriority(priority.getValue()));
            }
            if (!description.isEmpty()) {
                filterResult.retainAll(entityService.getAllPrescriptions().stream().filter(prescription ->
                        prescription.getDescription().toLowerCase().contains(description.getValue().toLowerCase()))
                        .collect(Collectors.toSet()));
            }
            prescriptionGrid.setItems(filterResult);
        });
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

        prescriptionGrid.asSingleSelect().addValueChangeListener(event -> {
            if (prescriptionGrid.asSingleSelect().isEmpty()) {
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
            else {
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });
    }
    public void update() {
        prescriptionGrid.setItems(entityService.getAllPrescriptions());
    }
}
