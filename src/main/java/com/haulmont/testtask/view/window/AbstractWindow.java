package com.haulmont.testtask.view.window;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;

public abstract class AbstractWindow extends Window {
    protected HorizontalLayout buttons = new HorizontalLayout();
    protected Button confirm = new Button("ОК");
    protected Button cancel = new Button("Отменить");

    protected AbstractWindow() {
        buttons.addComponents(confirm, cancel);
        confirm.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        cancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        cancel.addClickListener(clickEvent -> close());
    }
}
