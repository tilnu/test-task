package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.service.EntityService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

public class DoctorStatisticWindow extends Window {
    private final VerticalLayout mainLayout = new VerticalLayout();
    private final Grid<Doctor> doctorStatistic = new Grid<>();
    private final Button ok = new Button("ОК");

    public DoctorStatisticWindow(EntityService entityService) {
        doctorStatistic.addColumn(Doctor::getSurname).setCaption("Фамилия");
        doctorStatistic.addColumn(Doctor::getName).setCaption("Имя");
        doctorStatistic.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        doctorStatistic.addColumn(Doctor::getSpecialisation).setCaption("Специализация");
        doctorStatistic.addColumn(doctor ->
                entityService.findPrescriptionByDoctor(doctor).size()).setCaption("Количество рецептов");
        doctorStatistic.setItems(entityService.getAllDoctors());
        ok.addClickListener(clickEvent -> close());
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        mainLayout.addComponents(doctorStatistic, ok);
        mainLayout.setComponentAlignment(ok, Alignment.MIDDLE_CENTER);
        mainLayout.setSizeUndefined();
        setContent(mainLayout);
        setModal(true);
    }
}
