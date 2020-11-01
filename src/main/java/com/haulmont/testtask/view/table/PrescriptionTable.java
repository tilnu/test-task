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

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class PrescriptionTable extends AbstractTable {
    private final EntityService entityService;
    private final PrescriptionWindow prescriptionWindow;
    private final Grid<Prescription> prescriptionGrid = new Grid<>();
    private final HorizontalLayout filterPanel = new HorizontalLayout();
    private final ComboBox<Patient> filterPatient = new ComboBox<>("Пациент");
    private final ComboBox<Priority> filterPriority = new ComboBox<>("Приоритет");
    private final TextField filterDescription = new TextField("Описание");
    private final Button confirmFilter = new Button("Применить");

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
        addComponent(toolbar);
    }
    private void buildFilter() {
        filterPatient.addFocusListener(focusEvent -> filterPatient.setItems(entityService.getAllPatients()));
        filterPriority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);
        filterPanel.addComponents(filterPatient, filterPriority, filterDescription, confirmFilter);
        filterPanel.setComponentAlignment(confirmFilter, Alignment.BOTTOM_CENTER);
        addComponent(filterPanel);

        confirmFilter.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        confirmFilter.addClickListener(clickEvent -> {
            LinkedHashSet<Prescription> filterResult = new LinkedHashSet<>(entityService.getAllPrescriptions());
            if (!filterPatient.isEmpty()) {
                filterResult.retainAll(entityService.findPrescriptionByPatient(filterPatient.getValue()));
            }
            if (!filterPriority.isEmpty()) {
                filterResult.retainAll(entityService.findPrescriptionByPriority(filterPriority.getValue()));
            }
            if (!filterDescription.isEmpty()) {
                filterResult.retainAll(entityService.getAllPrescriptions().stream().filter(prescription ->
                        prescription.getDescription().toLowerCase().contains(filterDescription.getValue().toLowerCase()))
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
        prescriptionGrid.addColumn(Prescription::getValidDate).setCaption("Срок действия");
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
    private void update() {
        prescriptionGrid.setItems(entityService.getAllPrescriptions());
    }
}
