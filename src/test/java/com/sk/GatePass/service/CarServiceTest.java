package com.sk.GatePass.service;

import com.sk.GatePass.model.Car;
import com.sk.GatePass.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void testAddCar() {
        // Arrange
        Car car =  new Car("Toyota", "Camry","abcd");
        when(carRepository.save(car)).thenReturn(car);

        // Act
        Car result = carService.addCar(car);

        // Assert
        assertEquals(car, result);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void testGetCars() {
        // Arrange
        List<Car> cars = Arrays.asList(new Car("Toyota", "Camry", "ABC123"), new Car("Honda", "Accord", "XYZ789"));
        when(carRepository.findAll()).thenReturn(cars);

        // Act
        List<Car> result = carService.getCars();

        // Assert
        assertEquals(cars, result);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testGetCarById() {
        // Arrange
        Car car = new Car("Toyota", "Camry", "ABC123");
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        // Act
        Car result = carService.getCarById(1L);

        // Assert
        assertEquals(car, result);
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteCarById() {
        // Arrange

        // Act
        carService.deleteCarById(1L);

        // Assert
        verify(carRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateCarById() {
        // Arrange
        Car car = new Car("Toyota", "Camry", "ABC123");
        Car updatedCar = new Car("Toyota", "Corolla", "XYZ789");
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        // Act
        Car result = carService.updateCarById(1L, updatedCar);

        // Assert
        assertEquals(updatedCar.getModel(), result.getModel());
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).save(car);
    }
}
