package com.haulmont.testtask.view.window;

import com.vaadin.ui.*;

public class PatientWindow extends Window {
    VerticalLayout information = new VerticalLayout();
    FormLayout formLayout = new FormLayout();
    HorizontalLayout buttons = new HorizontalLayout();
    TextField surname = new TextField("Фамилия");
    TextField name = new TextField("Имя");
    TextField patronymic = new TextField("Отчество");
    TextField phone = new TextField("Номер телефона");
    Button confirm = new Button("ОК");
    Button cancel = new Button("Отменить");
    public PatientWindow() {
        formLayout.addComponents(surname, name, patronymic, phone);
        cancel.addClickListener(clickEvent -> close());
        buttons.addComponents(confirm, cancel);
        information.addComponents(formLayout, buttons);
        setContent(information);
        setModal(true);
    }
}
