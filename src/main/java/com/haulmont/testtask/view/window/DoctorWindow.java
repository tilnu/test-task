package com.haulmont.testtask.view.window;

import com.haulmont.testtask.entity.Doctor;
import com.haulmont.testtask.repository.DoctorRepository;
import com.vaadin.ui.*;

public class DoctorWindow extends Window {
    DoctorRepository doctorRepository;
    VerticalLayout information = new VerticalLayout();
    FormLayout formLayout = new FormLayout();
    HorizontalLayout buttons = new HorizontalLayout();
    TextField surname = new TextField("Фамилия");
    TextField name = new TextField("Имя");
    TextField patronymic = new TextField("Отчество");
    TextField specialisation = new TextField("Специализация");
    Button confirm = new Button("ОК");
    Button cancel = new Button("Отменить");
    public DoctorWindow(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        formLayout.addComponents(surname, name, patronymic, specialisation);
        cancel.addClickListener(clickEvent -> close());
        buttons.addComponents(confirm, cancel);
        information.addComponents(formLayout, buttons);
        setContent(information);
        setModal(true);
    }
}
