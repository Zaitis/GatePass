package com.sk.GatePass.view.admin.company;



import com.sk.GatePass.model.Company;
import com.sk.GatePass.service.CompanyService;
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
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Manage Companies")
@RolesAllowed("ADMIN")
@Route(value = "manage-companies", layout = AdminLayout.class)
public class ManageCompanyView extends VerticalLayout {

    Grid<Company> grid = new Grid<>(Company.class);
    TextField filterText= new TextField();
    CompanyForm form;
    private CompanyService companyService;


    public ManageCompanyView(CompanyService companyService){
        this.companyService = companyService;


        addClassName("company-view");
    setSizeFull();

    configureGrid();
    configureForm();

    add(
            getToolBar(),
            getContent()
        );

      updateCompanies();
      closeEditor();
    }

    private void closeEditor() {
        form.setCompany(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateCompanies() {
        grid.setItems(companyService.filterCompany(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1, form);
        content.addClassName("company");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new CompanyForm();
        form.setWidth("25em");

        form.addSaveListener(this::saveCompany);
        form.addDeleteListener(this::deleteCompany);
        form.addCloseListener(e -> closeEditor());

    }

    private void deleteCompany(CompanyForm.DeleteEvent event) {
        companyService.deleteCompany(event.getCompany().getId());
        updateCompanies();
        closeEditor();
    }

    private void saveCompany(CompanyForm.SaveEvent event){
        companyService.addCompany(event.getCompany());
        updateCompanies();
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateCompanies());
        Button addCompanyButton = new Button("Add company");
        addCompanyButton.addClickListener(e -> addCompany());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCompanyButton);
        return toolbar;
    }

    private void addCompany() {
        grid.asSingleSelect().clear();
        editContact(new Company());
    }

    private void configureGrid() {
        grid.addClassName("company-grid");
        grid.setSizeFull();
        grid.setColumns("id", "companyName", "phone", "mail");
        grid.addColumn(company -> company.getEmployees().size()).setHeader("CompanySize");
        grid.getColumns().forEach(col->col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));
    }

    private void editContact(Company company) {
        if(company == null){
            closeEditor();
        } else {
            form.setCompany(company);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}