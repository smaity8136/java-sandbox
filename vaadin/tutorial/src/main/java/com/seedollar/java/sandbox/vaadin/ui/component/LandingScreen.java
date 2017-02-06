package com.seedollar.java.sandbox.vaadin.ui.component;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by seedollar on 1/30/17.
 */
@UIScope
@SpringComponent
public class LandingScreen extends CustomComponent {

    @Autowired
    public LandingScreen(SpringViewProvider springViewProvider) {

        setResponsive(true);
        HorizontalLayout layout = new HorizontalLayout();
        layout.setResponsive(true);
        layout.setSizeFull();
        setCompositionRoot(layout);
        setSizeFull();
        Page.getCurrent().getStyles().add(".table-header {  font-weight: bold;   }");
        Page.getCurrent().getStyles().add(".daysLeftOk { color: yelow; background-color: green !important; }");
        Page.getCurrent().getStyles().add(".daysLeftOver{ color: yellow; background-color: red !important; }");
        Page.getCurrent().getStyles().add(".daysLeftAlmostOver { color: blue; background-color: orange !important; }");

        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();
        layout.addComponent(viewContainer);
        layout.setExpandRatio(viewContainer, 1f);

        Navigator navigator = new Navigator(UI.getCurrent(), viewContainer);
        // Without an AccessDeniedView, the view provider would act like the restricted views did not exist at all.
//        springViewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
        navigator.addProvider(springViewProvider);
//        navigator.setErrorView(ErrorView.class);
        navigator.navigateTo(navigator.getState());
    }


}
