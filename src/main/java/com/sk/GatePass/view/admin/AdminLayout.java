package com.sk.GatePass.view.admin;


import com.sk.GatePass.security.SecurityService;
import com.sk.GatePass.view.admin.car.ManageCarView;
import com.sk.GatePass.view.admin.company.ManageCompanyView;
import com.sk.GatePass.view.admin.gatePass.ManageGatePassView;
import com.sk.GatePass.view.admin.employee.ManageEmployeeView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class AdminLayout extends AppLayout {


    private SecurityService securityService;

    public AdminLayout(SecurityService securityService){
        this.securityService = securityService;

        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        Button adminDashboard = new Button("Dashboard");
        adminDashboard.addClickListener(e -> UI.getCurrent().navigate(AdminDashboardView.class));
        adminDashboard.setWidth("200px");
        Button employee = new Button("Users");
        employee.addClickListener(e -> UI.getCurrent().navigate(ManageEmployeeView.class));
        employee.setWidth("200px");
        Button company = new Button("Companies");
        company.addClickListener(e -> UI.getCurrent().navigate(ManageCompanyView.class));
        company.setWidth("200px");
        Button car = new Button("Cars");
        car.addClickListener(e -> UI.getCurrent().navigate(ManageCarView.class));
        car.setWidth("200px");
        Button gatePassView = new Button("Gate");
        gatePassView.addClickListener(e -> UI.getCurrent().navigate(ManageGatePassView.class));
        gatePassView.setWidth("200px");
        

        addToDrawer(new VerticalLayout(
                adminDashboard,
                employee,
                company,
                car,
                gatePassView
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
