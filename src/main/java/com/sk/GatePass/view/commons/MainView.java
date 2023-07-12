package com.sk.GatePass.view.commons;



import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route(value = "")
@AnonymousAllowed
public class MainView extends VerticalLayout {


    public MainView() {
        setWidth("80%");
        setAlignItems(Alignment.CENTER);
        


        H1 title = new H1("Welcome at Parking Manager");
        H3 paragraphTitle = new H3("How it works?");
        Paragraph paragraph = new Paragraph(
                "The application allows users to create an account," +
                        " choose the company they belong to, and add cars." +
                        " For each car, the user can request only one gate pass." +
                        " The administrators manage the application," +
                        " and they have access to an administrative panel where they can add companies and accept" +
                        " or reject gate pass requests."
        );



        Button loginView = new Button("Login");
        Button createView = new Button("Create Account");
        loginView.addClickListener(e -> UI.getCurrent().navigate(LoginView.class));
        loginView.addClickListener(e -> UI.getCurrent().navigate(CreateAccountView.class));
        Div spaceDiv = new Div();
        spaceDiv.setWidth("600px");

        HorizontalLayout header = new HorizontalLayout();
        header.add(title, loginView, createView);
        add(header, paragraphTitle, paragraph);


    }
}