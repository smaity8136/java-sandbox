package com.seedollar.java.sandbox.vaadin.ui;

import com.seedollar.java.sandbox.vaadin.ui.component.LandingScreen;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by seedollar on 1/30/17.
 */
@SpringUI
public class TutorialUI extends UI {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Securlogic SiteView");
        setContent(applicationContext.getBean(LandingScreen.class));
    }
}
