package com.haulmont.testtask.view.table;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.service.EntityService;
import com.haulmont.testtask.view.window.DoctorStatisticWindow;
import com.haulmont.testtask.view.window.DoctorWindow;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class DoctorTable extends VerticalLayout {

    private DoctorWindow doctorWindow;
    private EntityService entityService;
    private Grid<Doctor> doctorGrid = new Grid<>();
    private HorizontalLayout toolbar = new HorizontalLayout();
    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Изменить");
    private Button deleteButton = new Button("Удалить");
    private Button statisticsButton = new Button("Показать статистику");

    @Autowired
    public DoctorTable(EntityService entityService, DoctorWindow doctorWindow) {
        this.entityService = entityService;
        this.doctorWindow = doctorWindow;
        doctorWindow.addCloseListener(closeEvent -> update());
        buildToolbar();
        buildGrid();
        update();
    }
    private void buildToolbar() {
        addButton.addClickListener(clickEvent -> {
            doctorWindow.setDoctor(new Doctor());
            UI.getCurrent().addWindow(doctorWindow);

        });
        editButton.addClickListener(clickEvent -> {
            doctorWindow.setDoctor(doctorGrid.asSingleSelect().getValue());
            UI.getCurrent().addWindow(doctorWindow);
        });
        deleteButton.addClickListener(clickEvent -> {
            try {
                entityService.deleteDoctor(doctorGrid.asSingleSelect().getValue());
                update();
            }
            catch (Exception e) {
                Notification.show("Для данного врача есть рецепты");
            }
        });
        statisticsButton.addClickListener(clickEvent -> UI.getCurrent().addWindow(new DoctorStatisticWindow(entityService)));
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        toolbar.addComponents(addButton, editButton, deleteButton, statisticsButton);
        addComponent(toolbar);
    }
    private void buildGrid() {
        doctorGrid.addColumn(Doctor::getSurname).setCaption("Фамилия");
        doctorGrid.addColumn(Doctor::getName).setCaption("Имя");
        doctorGrid.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        doctorGrid.addColumn(Doctor::getSpecialisation).setCaption("Специализация");
        doctorGrid.setSizeFull();
        addComponent(doctorGrid);

        doctorGrid.asSingleSelect().addValueChangeListener(event -> {
            if (doctorGrid.asSingleSelect().isEmpty()) {
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
        doctorGrid.setItems(entityService.getAllDoctors());
    }
}