package com.sk.GatePass.view.user;

import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.EmployeeRepository;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("Add Car")
@Route(value= "add-car", layout = UserLayout.class)
@RolesAllowed("USER")
public class AddCarView extends VerticalLayout {


    private EmployeeRepository employeeRepository;

    public AddCarView(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String string = authentication.getName();
        Employee employee = employeeRepository.findByMail(string);
        System.out.println(employee);

        H1 title = new H1("Not implemnted yet.");
        add(title);
    }
}
