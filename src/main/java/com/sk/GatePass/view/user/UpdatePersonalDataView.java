package com.sk.GatePass.view.user;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Update Data")
@Route(value = "update-data", layout = UserLayout.class)
@RolesAllowed("USER")
public class UpdatePersonalDataView extends VerticalLayout {

    public UpdatePersonalDataView(){

        H1 title = new H1("Not implemnted yet.");
        add(title);
    }
}
