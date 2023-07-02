package com.sk.GatePass.view;

import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginFormView extends VerticalLayout {




    public LoginFormView() {
        LoginForm loginForm = new LoginForm();
        add(loginForm);

    }



}
