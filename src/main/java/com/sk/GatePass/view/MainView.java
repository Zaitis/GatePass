package com.sk.GatePass.view;



import com.sk.GatePass.view.admin.gatePass.ManageGatePassView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route(value = "")
@AnonymousAllowed
public class MainView extends VerticalLayout {


    public MainView() {
        RouterLink loginView = new RouterLink("Login", LoginView.class);
        RouterLink createView = new RouterLink("Create Account", CreateAccountView.class);
        add(loginView, createView);


    }
}