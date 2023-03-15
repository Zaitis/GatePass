package com.sk.GatePass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.GatePass.model.Car;
import com.sk.GatePass.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private List<Car> carList;
    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        carList = new ArrayList<>();
        car1 = new Car("Opel", "Corsa", "WW12345");
        car1.setId(1L);
        car2 = new Car("Fiat", "Panda", "SK12345");
        car2.setId(1L);
        carList.add(car1);
        carList.add(car2);
    }

    @Test
    void shouldReturnListOfCars() {
        when(carService.getCars()).thenReturn(carList);
        ResponseEntity<List<Car>> response = carController.getCars();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(carList);
    }

    @Test
    void shouldReturnCarById() {
        when(carService.getCarById(anyLong())).thenReturn(car1);
        ResponseEntity<Car> response = carController.getCar(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void shouldReturnNotFound() {
        when(carService.getCarById(anyLong())).thenReturn(null);
        ResponseEntity<Car> response = carController.getCar(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateNewCar() throws Exception {
        when(carService.addCar(any(Car.class))).thenReturn(car1);
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = objectMapper.writeValueAsString(car1);
        ResponseEntity<Car> response = carController.addCar(car1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void shouldUpdateExistingCar() {
        when(carService.updateCarById(anyLong(), any(Car.class))).thenReturn(car1);
        ResponseEntity<Car> response = carController.updateCar(1L, car1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void shouldDeleteExistingCar() {
        when(carService.getCarById(anyLong())).thenReturn(car1);
        ResponseEntity<?> response = carController.deleteCar(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(carService, times(1)).deleteCarById(1L);
    }

    @Test
    void shouldReturnCarNotFound() {
        when(carService.getCarById(anyLong())).thenReturn(null);
        ResponseEntity<?> response = carController.deleteCar(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(carService, never()).deleteCarById(anyLong());
    }
}
