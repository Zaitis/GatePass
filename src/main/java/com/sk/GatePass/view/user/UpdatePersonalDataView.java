package com.sk.GatePass.view.user;

import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.EmployeeRepository;
import com.sk.GatePass.service.CompanyService;
import com.sk.GatePass.service.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
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
    private TextField phone = new TextField("Phone number");
    private ComboBox<Company> company = new ComboBox<>("Company");

    private Button cancel = new Button("Cancel");
    private Button accept = new Button("Accept");

    private Employee employee;

    private EmployeeService employeeService;
    private CompanyService companyService;

    public UpdatePersonalDataView(EmployeeService employeeService, CompanyService companyService, EmployeeRepository repository) {
        this.employeeService = employeeService;
        this.companyService = companyService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        employee = employeeService.getEmployeeByMail(username);

        H1 title = new H1("Update your personal data");
        add(title, createFormLayout());
    }
    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();

        firstName.setValue(employee.getFirstName());
        lastName.setValue(employee.getLastName());
        phone.setValue(employee.getPhone());
        company.setItems(companyService.getCompanies());
        company.setItemLabelGenerator(Company::getCompanyName);
        company.setValue(employee.getCompany());

        cancel.addClickListener(e  -> UI.getCurrent().navigate(DashboardView.class));
        accept.addClickListener(e ->updateEmployee());
        accept.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        formLayout.add(firstName, lastName, phone, company, accept, cancel);
        return formLayout;
    }

    private void updateEmployee() {
        employee.setFirstName(firstName.getValue());
        employee.setLastName(lastName.getValue());
        employee.setPhone(phone.getValue());
        employee.setCompany(company.getValue());
        employeeService.updateEmployeeById(employee.getId(), employee);
        Notification.show("Update your personal data.");

    }
}
