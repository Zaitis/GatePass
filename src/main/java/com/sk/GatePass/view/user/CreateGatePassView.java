package com.sk.GatePass.view.user;

import com.sk.GatePass.controller.GatePassController;
import com.sk.GatePass.dto.GatePassDto;
import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.CarRepository;
import com.sk.GatePass.repository.EmployeeRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@PageTitle("Gate Pass")
@Route(value = "create-gate-pass", layout = UserLayout.class)
@RolesAllowed("USER")
public class CreateGatePassView extends VerticalLayout {
    ComboBox<Car> cars = new ComboBox<>();
    private CarRepository carRepository;
    private EmployeeRepository employeeRepository;
    private GatePassController controller;
    private Button accept = new Button("Ask for gate pass");

    public CreateGatePassView(CarRepository carRepository, EmployeeRepository employeeRepository, GatePassController controller) {
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;
        this.controller = controller;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String string = authentication.getName();
        Employee employee = employeeRepository.findByMail(string);
        Long id = employee.getId();
        List<Car> employeeCars =carRepository.findCarsByEmployeeId(id);
        cars.setItems(employeeCars);
        cars.setItemLabelGenerator(Car::getPlate);

        accept.addClickListener(e -> ask());

        H1 title = new H1("Request for gate pass for your cars.");
        add(title);
        add(cars, accept);


    }

    private void ask() {
        controller.addGatePass(new GatePassDto(cars.getValue().getId()));
    }
}
