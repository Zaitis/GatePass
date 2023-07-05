package com.sk.GatePass.view.user;

import com.sk.GatePass.controller.EmployeeController;
import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.EmployeeRepository;
import com.sk.GatePass.service.CompanyService;
import com.sk.GatePass.service.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("Update Data")
@Route(value = "update-data", layout = UserLayout.class)
@RolesAllowed("USER")
public class UpdatePersonalDataView extends VerticalLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField mail = new EmailField("Email address");
    private PasswordField password = new PasswordField("password");
    private TextField phone = new TextField("Phone number");
    private ComboBox<Company> company = new ComboBox<>("Company");

    private Button cancel = new Button("Cancel");
    private Button create = new Button("Create");

    private EmployeeService employeeService;
    private CompanyService companyService;
    private EmployeeRepository repository;

    public UpdatePersonalDataView(EmployeeService employeeService, CompanyService companyService, EmployeeRepository repository) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.repository = repository;


        H1 title = new H1("Not implemnted yet.");
        add(title, createFormLayout());



    }
    private Component createFormLayout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = repository.findByMail(username);
        firstName.setValue(employee.getFirstName());
        lastName.setValue(employee.getLastName());
        phone.setValue(employee.getPhone());
        mail.setValue(employee.getMail());
        FormLayout formLayout = new FormLayout();
        mail.setErrorMessage("Please enter a valid email address");
        company.setItems(companyService.getCompanies());
        company.setItemLabelGenerator(Company::getCompanyName);
        formLayout.add(firstName, lastName, password, phone, mail, company);
        return formLayout;
    }
}
