package com.sk.GatePass.view;



import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@Route(value = "", layout = MainLayout.class)
@PermitAll
public class MainView extends VerticalLayout {


    public MainView() {

    }
}