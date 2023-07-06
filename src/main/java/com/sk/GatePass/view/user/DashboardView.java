package com.sk.GatePass.view.user;

import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.CarRepository;
import com.sk.GatePass.repository.EmployeeRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Route(value = "dashboard", layout = UserLayout.class)
@PageTitle("dashboard")
@RolesAllowed("USER")
public class DashboardView extends VerticalLayout {

    private final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;
    private final Employee employee;

    public DashboardView(EmployeeRepository employeeRepository, CarRepository carRepository){
        this.employeeRepository=employeeRepository;
        this.carRepository=carRepository;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String string = authentication.getName();
        employee = employeeRepository.findByMail(string);



        H2 title = new H2("Hello " + employee.getFirstName() + " " + employee.getLastName() );

        add(
                title,
                ContextMenuBasic()
        );
    }
    public Component ContextMenuBasic() {

        H4 carTitle = new H4("Here you have list of your cars");
        List<Car> employeeCars =carRepository.findCarsByEmployeeId(employee.getId());

        Grid<Car> grid = new Grid();
        grid.setAllRowsVisible(true);
        grid.setItems(employeeCars);

        grid.addColumn(Car::getBrand).setHeader("Brand");
        grid.addColumn(Car::getModel).setHeader("Model");
        grid.addColumn(Car::getPlate).setHeader("Plate");

        return new VerticalLayout(carTitle, grid);
    }

}
