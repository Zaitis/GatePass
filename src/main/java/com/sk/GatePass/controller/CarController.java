package com.sk.GatePass.controller;

import com.sk.GatePass.dto.CarDto;
import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.service.CarService;

import com.sk.GatePass.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> carList = carService.getCars();
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable(value = "id") Long id) {
        Car car = carService.getCarById(id);
        if (car == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody CarDto newCar) {

        Car car = Car.builder()
                .brand(newCar.brand())
                .model(newCar.model())
                .plate(newCar.plate())
                .employee(employeeService.getEmployeeById(newCar.employee()).orElse(new Employee()))
                .build();
        Car createCar = carService.addCar(car);
        return new ResponseEntity<>(createCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long id, @RequestBody Car newCar) {
        Car car = carService.updateCarById(id, newCar);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable(value = "id") Long id) {
        Car deleteCar = carService.getCarById(id);
        if (deleteCar != null) {
            carService.deleteCarById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
