package com.sk.GatePass.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sk.GatePass.dto.CarDto;
import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.repository.CarRepository;
import com.sk.GatePass.repository.EmployeeRepository;
import com.sk.GatePass.service.CarService;
import com.sk.GatePass.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class CarControllerTest {

    @Test
    void testGetCars() {
        CarRepository carRepository = mock(CarRepository.class);
        CarService carService = new CarService(carRepository);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

        when(carRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Car>> actualCars = (new CarController(carService,
                new EmployeeService(employeeRepository))).getCars();
        assertTrue(actualCars.hasBody());
        assertEquals(200, actualCars.getStatusCodeValue());
        assertTrue(actualCars.getHeaders().isEmpty());
        verify(carRepository).findAll();
    }



    @Test
    void testGetCars3() {


        CarService carService = mock(CarService.class);
        when(carService.getCars()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Car>> actualCars = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).getCars();
        assertTrue(actualCars.hasBody());
        assertEquals(200, actualCars.getStatusCodeValue());
        assertTrue(actualCars.getHeaders().isEmpty());
        verify(carService).getCars();
    }


    @Test
    void testGetCar() {


        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        CarService carService = new CarService(carRepository);
        ResponseEntity<Car> actualCar = (new CarController(carService, new EmployeeService(mock(EmployeeRepository.class))))
                .getCar(1L);
        assertTrue(actualCar.hasBody());
        assertTrue(actualCar.getHeaders().isEmpty());
        assertEquals(200, actualCar.getStatusCodeValue());
        verify(carRepository).findById((Long) any());
    }



    @Test
    void testGetCar3() {


        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById((Long) any())).thenReturn(Optional.empty());
        CarService carService = new CarService(carRepository);
        ResponseEntity<Car> actualCar = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).getCar(1L);
        assertNull(actualCar.getBody());
        assertEquals(404, actualCar.getStatusCodeValue());
        assertTrue(actualCar.getHeaders().isEmpty());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testGetCar4() {


        CarService carService = mock(CarService.class);
        when(carService.getCarById((Long) any())).thenReturn(new Car());
        ResponseEntity<Car> actualCar = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).getCar(1L);
        assertTrue(actualCar.hasBody());
        assertTrue(actualCar.getHeaders().isEmpty());
        assertEquals(200, actualCar.getStatusCodeValue());
        verify(carService).getCarById((Long) any());
    }


    @Test
    void testAddCar() {


        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.save((Car) any())).thenReturn(new Car());
        CarService carService = new CarService(carRepository);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.of(new Employee()));
        CarController carController = new CarController(carService, new EmployeeService(employeeRepository));
        ResponseEntity<Car> actualAddCarResult = carController.addCar(new CarDto("Brand", "Model", "Plate", 1L));
        assertTrue(actualAddCarResult.hasBody());
        assertTrue(actualAddCarResult.getHeaders().isEmpty());
        assertEquals(201, actualAddCarResult.getStatusCodeValue());
        verify(carRepository).save((Car) any());
        verify(employeeRepository).findById((Long) any());
    }


    @Test
    void testAddCar3() {


        CarService carService = mock(CarService.class);
        when(carService.addCar((Car) any())).thenReturn(new Car());
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.of(new Employee()));
        CarController carController = new CarController(carService, new EmployeeService(employeeRepository));
        ResponseEntity<Car> actualAddCarResult = carController.addCar(new CarDto("Brand", "Model", "Plate", 1L));
        assertTrue(actualAddCarResult.hasBody());
        assertTrue(actualAddCarResult.getHeaders().isEmpty());
        assertEquals(201, actualAddCarResult.getStatusCodeValue());
        verify(carService).addCar((Car) any());
        verify(employeeRepository).findById((Long) any());
    }


    @Test
    void testAddCar5() {

        CarService carService = mock(CarService.class);
        when(carService.addCar((Car) any())).thenReturn(new Car());
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.getEmployeeById((Long) any())).thenReturn(Optional.of(new Employee()));
        CarController carController = new CarController(carService, employeeService);
        ResponseEntity<Car> actualAddCarResult = carController.addCar(new CarDto("Brand", "Model", "Plate", 1L));
        assertTrue(actualAddCarResult.hasBody());
        assertTrue(actualAddCarResult.getHeaders().isEmpty());
        assertEquals(201, actualAddCarResult.getStatusCodeValue());
        verify(carService).addCar((Car) any());
        verify(employeeService).getEmployeeById((Long) any());
    }



    @Test
    void testUpdateCar() {


        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.save((Car) any())).thenReturn(new Car());
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        CarService carService = new CarService(carRepository);
        CarController carController = new CarController(carService, new EmployeeService(mock(EmployeeRepository.class)));
        ResponseEntity<Car> actualUpdateCarResult = carController.updateCar(1L, new Car());
        assertTrue(actualUpdateCarResult.hasBody());
        assertTrue(actualUpdateCarResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateCarResult.getStatusCodeValue());
        verify(carRepository).save((Car) any());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testUpdateCar2() {


        Car car = mock(Car.class);
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setPlate((String) any());
        Optional<Car> ofResult = Optional.of(car);
        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.save((Car) any())).thenReturn(new Car());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        CarService carService = new CarService(carRepository);
        CarController carController = new CarController(carService, new EmployeeService(mock(EmployeeRepository.class)));
        ResponseEntity<Car> actualUpdateCarResult = carController.updateCar(1L, new Car());
        assertTrue(actualUpdateCarResult.hasBody());
        assertTrue(actualUpdateCarResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateCarResult.getStatusCodeValue());
        verify(carRepository).save((Car) any());
        verify(carRepository).findById((Long) any());
        verify(car).setBrand((String) any());
        verify(car).setModel((String) any());
        verify(car).setPlate((String) any());
    }



    @Test
    void testUpdateCar4() {


        Car car = mock(Car.class);
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setPlate((String) any());
        Optional.of(car);
        CarService carService = mock(CarService.class);
        when(carService.updateCarById((Long) any(), (Car) any())).thenReturn(new Car());
        CarController carController = new CarController(carService, new EmployeeService(mock(EmployeeRepository.class)));
        ResponseEntity<Car> actualUpdateCarResult = carController.updateCar(1L, new Car());
        assertTrue(actualUpdateCarResult.hasBody());
        assertTrue(actualUpdateCarResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateCarResult.getStatusCodeValue());
        verify(carService).updateCarById((Long) any(), (Car) any());
    }


    @Test
    void testDeleteCar() {


        CarRepository carRepository = mock(CarRepository.class);
        doNothing().when(carRepository).deleteById((Long) any());
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        CarService carService = new CarService(carRepository);
        ResponseEntity<?> actualDeleteCarResult = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).deleteCar(1L);
        assertNull(actualDeleteCarResult.getBody());
        assertEquals(200, actualDeleteCarResult.getStatusCodeValue());
        assertTrue(actualDeleteCarResult.getHeaders().isEmpty());
        verify(carRepository).findById((Long) any());
        verify(carRepository).deleteById((Long) any());
    }


    @Test
    void testDeleteCar3() {

        CarRepository carRepository = mock(CarRepository.class);
        doNothing().when(carRepository).deleteById((Long) any());
        when(carRepository.findById((Long) any())).thenReturn(Optional.empty());
        CarService carService = new CarService(carRepository);
        ResponseEntity<?> actualDeleteCarResult = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).deleteCar(1L);
        assertNull(actualDeleteCarResult.getBody());
        assertEquals(404, actualDeleteCarResult.getStatusCodeValue());
        assertTrue(actualDeleteCarResult.getHeaders().isEmpty());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testDeleteCar4() {


        CarService carService = mock(CarService.class);
        doNothing().when(carService).deleteCarById((Long) any());
        when(carService.getCarById((Long) any())).thenReturn(new Car());
        ResponseEntity<?> actualDeleteCarResult = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).deleteCar(1L);
        assertNull(actualDeleteCarResult.getBody());
        assertEquals(200, actualDeleteCarResult.getStatusCodeValue());
        assertTrue(actualDeleteCarResult.getHeaders().isEmpty());
        verify(carService).getCarById((Long) any());
        verify(carService).deleteCarById((Long) any());
    }
}