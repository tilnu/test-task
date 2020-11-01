package com.haulmont.testtask.view.window;

import com.vaadin.ui.*;

public abstract class AbstractPersonWindow extends AbstractWindow{
    protected VerticalLayout mainLayout = new VerticalLayout();
    protected FormLayout formLayout = new FormLayout();
    protected TextField surname = new TextField("Фамилия");
    protected TextField name = new TextField("Имя");
    protected TextField patronymic = new TextField("Отчество");

    protected AbstractPersonWindow() {
        surname.setRequiredIndicatorVisible(true);
        name.setRequiredIndicatorVisible(true);
        patronymic.setRequiredIndicatorVisible(true);

        formLayout.addComponents(surname, name, patronymic);
        formLayout.setSizeUndefined();

        mainLayout.addComponents(formLayout, buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);

        setContent(mainLayout);
        setResizable(false);
        setModal(true);
    }
}
