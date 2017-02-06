package com.seedollar.java.sandbox.vaadin.ui.view;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by seedollar on 1/30/17.
 */
@SpringView(name = "")
public class MainView extends VerticalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSpacing(true);
        setMargin(true);

        Label header = new Label("Welcome to the Vaadin Tutorial App");
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);

        Label body = new Label("<p>This is the main landing page!!!</p>");

        body.setContentMode(ContentMode.HTML);
        addComponent(body);

        // Have a clickable button
        addComponent(new Button("Push Me!",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent e) {
                        Notification.show("Pushed!", "A button was pushed", Notification.Type.WARNING_MESSAGE);
                    }
                }));

    }
}
