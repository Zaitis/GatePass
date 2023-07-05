package com.sk.GatePass.view.user;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Add Car")
@Route(value= "add-car", layout = UserLayout.class)
@RolesAllowed("USER")
public class AddCarView extends VerticalLayout {

    public AddCarView(){

        H1 title = new H1("Not implemnted yet.");
        add(title);
    }
}
