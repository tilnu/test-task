package com.haulmont.testtask;

import com.haulmont.testtask.view.table.DoctorTable;
import com.haulmont.testtask.view.table.PatientTable;
import com.haulmont.testtask.view.table.PrescriptionTable;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    private final TabSheet selector = new TabSheet();
    private PatientTable patientTable = new PatientTable();
    private DoctorTable doctorTable = new DoctorTable();
    private PrescriptionTable prescriptionTable = new PrescriptionTable();
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        selector.addTab(patientTable, "Patients");
        selector.addTab(doctorTable, "Doctors");
        selector.addTab(prescriptionTable, "Prescriptions");
        layout.addComponent(selector);

        setContent(layout);
    }
}