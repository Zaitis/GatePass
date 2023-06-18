package com.sk.GatePass.service;

import com.sk.GatePass.model.Car;
import com.sk.GatePass.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {


    private final CarRepository carRepository;


    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public void deleteCarById(Long id){
        carRepository.deleteById(id);
    }

    public Car updateCarById(Long id, Car car) {
        Car oldCar = updateOldCar(id, car);

        return carRepository.save(oldCar);
    }

    private Car updateOldCar(Long id, Car car) {
        Car oldCar=carRepository.findById(id).get();
        oldCar.setBrand(car.getBrand());
        oldCar.setModel(car.getModel());
        oldCar.setPlate(car.getPlate());
        return oldCar;
    }

}
