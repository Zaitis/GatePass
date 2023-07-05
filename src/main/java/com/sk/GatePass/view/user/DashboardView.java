package com.sk.GatePass.view.user;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "dashboard", layout = UserLayout.class)
@PageTitle("dashboard")
@RolesAllowed("USER")
public class DashboardView extends VerticalLayout {



    public DashboardView(){

        H1 title = new H1("Not implemnted yet.");
        add(title);
    }

}
