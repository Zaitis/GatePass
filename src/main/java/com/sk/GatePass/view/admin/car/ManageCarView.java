package com.sk.GatePass.view.admin.car;


import com.sk.GatePass.model.Car;
import com.sk.GatePass.service.CarService;
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

@PageTitle("Manage Cars")
@RolesAllowed("ADMIN")
@Route(value = "manage-cars", layout = AdminLayout.class)
public class ManageCarView extends VerticalLayout {

    Grid<Car> grid = new Grid<>(Car.class);
    TextField filterText= new TextField();
    CarForm form;
    private CarService carService;

    public ManageCarView(CarService carService){
        this.carService = carService;

        addClassName("car-view");
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
        form.setCompany(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateCars() {
        grid.setItems(carService.filterCar(filterText.getValue()));
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
        form = new CarForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveCompany);
        form.addDeleteListener(this::deleteCompany);
        form.addCloseListener(e -> closeEditor());
    }

    private void deleteCompany(CarForm.DeleteEvent event) {
        carService.deleteCarById(event.getCar().getId());
        updateCars();
        closeEditor();
    }

    private void saveCompany(CarForm.SaveEvent event){
        carService.addCar(event.getCar());
        updateCars();
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateCars());
        Button addCarButton = new Button("Add car");
        addCarButton.addClickListener(e -> addCar());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCarButton);
        return toolbar;
    }

    private void addCar() {
        grid.asSingleSelect().clear();
        editContact(new Car());
    }

    private void configureGrid() {
        grid.addClassName("company-grid");
        grid.setSizeFull();
        grid.setColumns("id","brand", "model", "plate");
   //     grid.addColumn(company -> company.getEmployees().size()).setHeader("CompanySize");
    //    grid.getColumns().forEach(col->col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));
    }

    private void editContact(Car car) {
        if(car == null){
            closeEditor();
        } else {
            form.setCompany(car);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}