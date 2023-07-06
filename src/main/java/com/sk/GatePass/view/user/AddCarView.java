package com.sk.GatePass.view.user;


import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.EmployeeRepository;
import com.sk.GatePass.service.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@PageTitle("Add Car")
@Route(value= "add-car", layout = UserLayout.class)
@RolesAllowed("USER")
public class AddCarView extends VerticalLayout {
    TextField brand = new TextField("Car Brand");
    TextField model = new TextField("Car Model");
    TextField plate = new TextField("Car Plate");

    Button save = new Button("Save");
  //  Button cancel = new Button("Cancel");
    private Car car;


    private EmployeeRepository employeeRepository;
    private CarService carService;
    @PersistenceContext
    private EntityManager entityManager;

    public AddCarView(EmployeeRepository employeeRepository, CarService carService){

        H1 title = new H1("Add your car to database");
        add(title);
        add(
                brand,
                model,
                plate,

                createButtonLayout()
        );
        this.carService = carService;

        this.employeeRepository = employeeRepository;


    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        save.addClickListener(event ->saveCar());
        save.addClickShortcut(Key.ENTER);


        return new HorizontalLayout(save );
    }

    private void saveCar() {

        Car car = new Car();
        car.setBrand(brand.getValue());
        car.setModel(model.getValue());
        car.setPlate(plate.getValue());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeRepository.findByMail(username);
        car.setEmployee(employee);

        carService.addCar(car);
        Notification.show("Car added to database.");
        UI.getCurrent().navigate("/dashboard");
    }
}
