package com.haulmont.testtask;

import com.haulmont.testtask.view.table.DoctorTable;
import com.haulmont.testtask.view.table.PatientTable;
import com.haulmont.testtask.view.table.PrescriptionTable;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    private final TabSheet selector = new TabSheet();
    @Autowired
    private PatientTable patientTable;
    @Autowired
    private DoctorTable doctorTable;
    @Autowired
    private PrescriptionTable prescriptionTable;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        selector.addTab(patientTable, "Пациенты");
        selector.addTab(doctorTable, "Врачи");
        selector.addTab(prescriptionTable, "Рецепты");
        layout.addComponent(selector);
        setContent(layout);
    }
}