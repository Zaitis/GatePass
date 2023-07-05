package com.sk.GatePass.view.user;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class UserLayout extends AppLayout {



    public UserLayout(){

        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink addCar = new RouterLink("Add Car", AddCarView.class);
        RouterLink createGatePass = new RouterLink("create gate pass", CreateGatePassView.class);
        RouterLink dashboard = new RouterLink("dashboard", DashboardView.class);
        RouterLink updatePersonalData = new RouterLink("update personal data", UpdatePersonalDataView.class);



        addToDrawer(new VerticalLayout(
                dashboard,
                addCar,
                createGatePass,
                updatePersonalData

        ));

    }

    private void createHeader() {
        H1 logo = new H1("Parking Manager");
        logo.addClassNames("text-l", "m-m");
    //    Button logout = new Button("Log out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }
}
