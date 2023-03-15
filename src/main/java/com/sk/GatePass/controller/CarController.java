package com.sk.GatePass.controller;

import com.sk.GatePass.model.Car;
import com.sk.GatePass.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {


    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> carList =carService.getCars();
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable(value ="id") Long id) {
        Car car= carService.getCarById(id);
        if(car==null)
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(car,HttpStatus.OK);
    }
    @PostMapping(value = "/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car createCar= carService.addCar(car);
        return new ResponseEntity<>(createCar,HttpStatus.CREATED);
    }
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value ="id") Long id,@RequestBody Car newCar) {
        Car car= carService.updateCarById(id, newCar);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable(value ="id") Long id) {
        Car deleteCar =carService.getCarById(id);
        if (deleteCar!=null) {
            carService.deleteCarById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
