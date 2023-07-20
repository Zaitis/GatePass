package com.sk.GatePass.view.user;

import com.sk.GatePass.security.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserLayout extends AppLayout {


    private SecurityService securityService;

    public UserLayout(SecurityService securityService){
        this.securityService = securityService;

        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        Button addCar = new Button("Add Car");
        addCar.setWidth("200px");
        addCar.addClickListener(e -> UI.getCurrent().navigate(AddCarView.class));
        Button createGatePass = new Button("Make Gate Pass");
        createGatePass.setWidth("200px");
        createGatePass.addClickListener(e -> UI.getCurrent().navigate(CreateGatePassView.class));
        Button dashboard = new Button("Dashboard");
        dashboard.setWidth("200px");
        dashboard.addClickListener(e -> UI.getCurrent().navigate(DashboardView.class));
        Button updatePersonalData = new Button("Update Personal Data");
        updatePersonalData.setWidth("200px");
        updatePersonalData.addClickListener(e -> UI.getCurrent().navigate(UpdatePersonalDataView.class));

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
        Button logout = new Button("Log out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }
}
