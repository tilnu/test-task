package com.haulmont.testtask.view.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractTable extends VerticalLayout {
    protected HorizontalLayout toolbar = new HorizontalLayout();
    protected Button addButton = new Button("Добавить");
    protected Button editButton = new Button("Изменить");
    protected Button deleteButton = new Button("Удалить");

    protected AbstractTable() {
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        toolbar.addComponents(addButton, editButton, deleteButton);
    }
}
