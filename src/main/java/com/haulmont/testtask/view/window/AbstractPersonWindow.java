package com.haulmont.testtask.view.window;

import com.vaadin.ui.*;

public abstract class AbstractPersonWindow extends Window{
    protected VerticalLayout mainLayout = new VerticalLayout();
    protected FormLayout formLayout = new FormLayout();
    protected HorizontalLayout buttons = new HorizontalLayout();
    protected TextField surname = new TextField("Фамилия");
    protected TextField name = new TextField("Имя");
    protected TextField patronymic = new TextField("Отчество");
    protected Button confirm = new Button("ОК");
    protected Button cancel = new Button("Отменить");
    protected AbstractPersonWindow() {
        surname.setRequiredIndicatorVisible(true);
        name.setRequiredIndicatorVisible(true);
        patronymic.setRequiredIndicatorVisible(true);

        formLayout.addComponents(surname, name, patronymic);
        formLayout.setSizeUndefined();

        cancel.addClickListener(clickEvent -> close());
        buttons.addComponents(confirm, cancel);

        mainLayout.addComponents(formLayout, buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);

        setContent(mainLayout);
        setResizable(false);
        setModal(true);
    }
}
