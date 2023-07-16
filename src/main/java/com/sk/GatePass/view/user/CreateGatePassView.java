package com.sk.GatePass.view.user;

import com.sk.GatePass.controller.GatePassController;
import com.sk.GatePass.dto.GatePassDto;
import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.repository.CarRepository;
import com.sk.GatePass.repository.EmployeeRepository;
import com.sk.GatePass.service.CarService;
import com.sk.GatePass.service.EmployeeService;
import com.sk.GatePass.service.GatePassService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Gate Pass")
@Route(value = "create-gate-pass", layout = UserLayout.class)
@RolesAllowed("USER")
public class CreateGatePassView extends VerticalLayout {
    ComboBox<Car> cars = new ComboBox<>();

    private CarService carService;
    private EmployeeService employeeService;
    private GatePassService gatePassService;
    private Button accept = new Button("Ask for gate pass");

    public CreateGatePassView(CarService carService, EmployeeService employeeService, GatePassService gatePassService) {
        this.carService= carService;
        this.employeeService = employeeService;
        this.gatePassService = gatePassService;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String string = authentication.getName();
        Employee employee = employeeService.getEmployeeByMail(string);
        Long id = employee.getId();
        List<Car> employeeCars =carService.findCarsByEmployeeId(id);
        List<GatePass> allGatePass = gatePassService.getGatePasses();
        List<Car> carsWithGatePass = new ArrayList<>();
        for (GatePass gatePass : allGatePass) {
            for (Car car : employeeCars) {

                if (gatePass.getCars().getId().equals(car.getId())) {
                    carsWithGatePass.add(car);
                    break;
                }
            }
        }
            employeeCars.removeAll(carsWithGatePass);
        cars.setItems(employeeCars);
        cars.setItemLabelGenerator(Car::getPlate);
        accept.addClickListener(e -> ask());

        H1 title = new H1("Request for gate pass for your cars.");
        add(title);
        add(cars, accept);
    }

    private void ask() {

        gatePassService.updateGatePass(cars.getValue().getGatePass().getId(), cars.getValue().getGatePass());
        UI.getCurrent().navigate("/dashboard");
    }
}
