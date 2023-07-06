package com.sk.GatePass.view.admin;


import com.sk.GatePass.security.SecurityService;
import com.sk.GatePass.view.admin.car.ManageCarView;
import com.sk.GatePass.view.admin.company.ManageCompanyView;
import com.sk.GatePass.view.admin.gatePass.ManageGatePassView;
import com.sk.GatePass.view.admin.employee.ManageEmployeeView;
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
        RouterLink adminDashboard = new RouterLink("dashboard", AdminDashboardView.class);
        RouterLink employee = new RouterLink("employee", ManageEmployeeView.class);
        RouterLink company = new RouterLink("company", ManageCompanyView.class);
        RouterLink car = new RouterLink("car", ManageCarView.class);
        RouterLink gatePassView = new RouterLink("gate pass", ManageGatePassView.class);
        company.setHighlightCondition(HighlightConditions.sameLocation());

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
