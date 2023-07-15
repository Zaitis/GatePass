package com.sk.GatePass.view.commons;



import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;


@Route(value = "")
@AnonymousAllowed
@Theme("main")
public class MainView extends VerticalLayout implements AppShellConfigurator {


    public MainView() {
        setClassName("main");
        setWidth("80%");
        setAlignItems(Alignment.CENTER);
        


        H1 title = new H1("Welcome at Parking Manager.");
        H3 paragraphTitle = new H3("How it works?");
        Paragraph intro = new Paragraph("Welcome to the Parking Manager demo application. This is a brief introduction to how the application works. All technical matters are discussed in the README on GitHub. From this page, you can navigate to the login page or create a new user account. If you don't wish to create an account and want to test the application, you can use these login credentials: test@test.pl/test.");

        Paragraph afterLogin = new Paragraph("After logging in, there are four views available: \n" +
                "1. Dashboard: Here, you can see a list of your cars. Information about gate passes will be added in the next update. \n" +
                "2. Add Car: This is where you can add your car to the database. \n" +
                "3. Create gate pass: Here, you can request a gate pass. Once you submit a request, you won't be able to request another gate pass for the same car. \n" +
                "4. Update personal data: This is where you can edit your details. This option will be available in the next update. \n" +
                "Additionally, there's a logout option which allows you to sign out of the application.");

        Paragraph admin = new Paragraph("If you want to check out the admin account, use these login credentials: admin@admin.pl/admin. Please note, the \"filter by...\" option is currently unavailable in all views. As an admin, you have control over: \n" +
                "1. Users (to be renamed \"users\" in the next update). You can add a new user (who will be given the default password \"password\"), and you can edit or delete users by clicking on a record. \n" +
                "2. Companies - similar to the functionality available for users. \n" +
                "3. Cars - similar to the functionality available for users. \n" +
                "4. Gate passes - these can be approved or rejected. If a user's car is rejected, they won't be able to request a new gate pass for that car.");





        Button loginView = new Button("Login");
        Button createView = new Button("Create Account");
        loginView.addClickListener(e -> UI.getCurrent().navigate(LoginView.class));
        createView.addClickListener(e -> UI.getCurrent().navigate(CreateAccountView.class));
        Div spaceDiv = new Div();
        spaceDiv.setWidth("600px");

        HorizontalLayout header = new HorizontalLayout();
        header.add(title, loginView, createView);
        add(header, paragraphTitle,intro, afterLogin, admin );


    }
}