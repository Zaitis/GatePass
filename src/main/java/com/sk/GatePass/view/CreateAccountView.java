package com.sk.GatePass.view;


import com.sk.GatePass.controller.EmployeeController;
import com.sk.GatePass.dto.EmployeeDto;
import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.model.Role;
import com.sk.GatePass.service.CompanyService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@PageTitle("Create Account")
@Route(value = "create-account")
@AnonymousAllowed
public class CreateAccountView extends VerticalLayout {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField mail = new EmailField("Email address");
    private PasswordField password = new PasswordField("password");
    private TextField phone = new TextField("Phone number");
    private ComboBox<Company> company = new ComboBox<>("Company");

    private Button clear = new Button("Clear");
    private Button cancel = new Button("Cancel");
    private Button create = new Button("Create");

    private Binder<Employee> binder = new Binder<>(Employee.class);
    private EmployeeController employeeController;
    private CompanyService companyService;


    public CreateAccountView(EmployeeController employeeController, CompanyService companyService) {
        this.employeeController = employeeController;
        this.companyService = companyService;
        addClassName("create-account-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        clear.addClickListener(e -> clearForm());
        create.addClickListener(e -> {
            employeeController.addEmployee(getBuild());

            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private EmployeeDto getBuild() {
        return EmployeeDto.builder()
                .firstName(binder.getBean().getFirstName())
                .lastName(binder.getBean().getLastName())
                .mail(binder.getBean().getMail())
                .password(binder.getBean().getPassword())
                .phone(binder.getBean().getPhone())
                .company(binder.getBean().getCompany().getId())
                .role(binder.getBean().getRole() != null ? binder.getBean().getRole() : Role.USER)
                .build();
    }

    private void clearForm() {
        binder.setBean(new Employee());
    }

    private Component createTitle() {
        return new H3("Create account");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        mail.setErrorMessage("Please enter a valid email address");
        company.setItems(companyService.getCompanies());
        company.setItemLabelGenerator(Company::getCompanyName);
        formLayout.add(firstName, lastName, password, phone, mail, company);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(create);
        buttonLayout.add(clear);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}


