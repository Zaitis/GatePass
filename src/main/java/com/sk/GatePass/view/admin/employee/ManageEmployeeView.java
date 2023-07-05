package com.sk.GatePass.view.admin.employee;


import com.sk.GatePass.model.Employee;
import com.sk.GatePass.service.CompanyService;
import com.sk.GatePass.service.EmployeeService;
import com.sk.GatePass.view.admin.AdminLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Manage Employee")
@AnonymousAllowed
//@RolesAllowed("ADMIN")
@Route(value = "manage-employees", layout = AdminLayout.class)
public class ManageEmployeeView extends VerticalLayout {

    Grid<Employee> grid = new Grid<>(Employee.class);
    TextField filterText= new TextField();
    EmployeeForm form;
    private EmployeeService employeeService;
    private CompanyService companyService;


    public ManageEmployeeView(EmployeeService employeeService, CompanyService companyService ){
        this.employeeService = employeeService;
        this.companyService = companyService;


        addClassName("employee-view");
    setSizeFull();

    configureGrid();
    configureForm();

    add(
            getToolBar(),
            getContent()
        );

      updateCars();
      closeEditor();
    }

    private void closeEditor() {
        form.setEmployee(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateCars() {
        grid.setItems(employeeService.filterEmployee(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1, form);
        content.addClassName("car");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new EmployeeForm();
        form.setWidth("25em");

        form.addSaveListener(this::saveCompany);
        form.addDeleteListener(this::deleteCompany);
        form.addCloseListener(e -> closeEditor());

    }

    private void deleteCompany(EmployeeForm.DeleteEvent event) {
        employeeService.deleteEmployeeById(event.getEmployee().getId());
        updateCars();
        closeEditor();
    }

    private void saveCompany(EmployeeForm.SaveEvent event){
        Employee employee = event.getEmployee();
        employee.setCompany(companyService.getCompany(1L));
        employeeService.addEmployee(event.getEmployee());
        updateCars();
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateCars());
        Button addEmployeeButton = new Button("Add employee");
        addEmployeeButton.addClickListener(e -> addEmployee());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addEmployeeButton);
        return toolbar;
    }

    private void addEmployee() {
        grid.asSingleSelect().clear();
        editEmployee(new Employee());
    }

    private void configureGrid() {


        grid.addClassName("employee-grid");
        grid.setSizeFull();
        grid.setColumns("id","firstName","lastName", "phone", "mail", "role");
        grid.addColumn(employee -> employee.getCompany().getCompanyName()).setHeader("Company Name");
        grid.getColumns().forEach(col->col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editEmployee(e.getValue()));
    }

    private void editEmployee(Employee employee) {
        if(employee == null){
            closeEditor();
        } else {
            form.setEmployee(employee);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}