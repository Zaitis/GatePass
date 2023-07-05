package com.sk.GatePass.view.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "admin-dashboard", layout = AdminLayout.class)
@PageTitle("dashboard")
@RolesAllowed("ADMIN")
public class AdminDashboardView extends VerticalLayout {



    public AdminDashboardView(){

        H1 title = new H1("Not implemnted yet.");
        add(title);
    }

}
