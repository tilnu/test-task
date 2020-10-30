package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.repository.DoctorRepository;
import com.haulmont.testtask.view.window.DoctorWindow;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class DoctorTable extends VerticalLayout {
    @Autowired
    DoctorRepository doctorRepository;
    Grid<Doctor> doctorGrid = new Grid<>();
    HorizontalLayout toolbar = new HorizontalLayout();
    Button addButton = new Button("Добавить");
    Button editButton = new Button("Изменить");
    Button deleteButton = new Button("Удалить");
    Button statisticsButton = new Button("Показать статистику");
    public DoctorTable() {
        buildToolbar();
        buildGrid();
    }
    void buildToolbar() {
        addButton.addClickListener(clickEvent -> getUI().addWindow(new DoctorWindow(doctorRepository)));
        toolbar.addComponents(addButton, editButton, deleteButton, statisticsButton);
        addComponent(toolbar);
    }
    void buildGrid() {
        doctorGrid.addColumn(Doctor::getSurname).setCaption("Фамилия");
        doctorGrid.addColumn(Doctor::getName).setCaption("Имя");
        doctorGrid.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        doctorGrid.addColumn(Doctor::getSpecialisation).setCaption("Специализация");
        doctorGrid.setSizeFull();
        addComponent(doctorGrid);
    }
}