package com.sk.GatePass.controller;


import com.sk.GatePass.model.Car;
import com.sk.GatePass.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        car1 = new Car("Opel", "Corsa", "WW12345");
        car1.setId(1L);
        car2 = new Car("Fiat", "Panda", "SK12345");
        car2.setId(2L);

    }

    @Test
    void shouldReturnAllCars() {

        // given
        carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        when(carService.getCars()).thenReturn(carList);

        // when
        ResponseEntity<List<Car>> response = carController.getCars();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(carList);
    }

    @Test
    void shouldReturnCarById() {

        // given
        when(carService.getCarById(any())).thenReturn(car1);

        // when
        ResponseEntity<Car> response = carController.getCar(1L);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void shouldReturnNotFound() {

        // given
        when(carService.getCarById(anyLong())).thenReturn(null);

        // when
        ResponseEntity<Car> response = carController.getCar(1L);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateNewCar() {

        // given
        when(carService.addCar(any(Car.class))).thenReturn(car1);

        // when
        ResponseEntity<Car> response = carController.addCar(car1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void shouldUpdateExistingCar() {

        // given
        when(carService.updateCarById(anyLong(), any(Car.class))).thenReturn(car1);

        // when
        ResponseEntity<Car> response = carController.updateCar(1L, car1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void shouldDeleteExistingCar() {

        // given
        when(carService.getCarById(anyLong())).thenReturn(car1);

        // when
        ResponseEntity<?> response = carController.deleteCar(1L);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(carService, times(1)).deleteCarById(1L);
    }

    @Test
    void shouldReturnCarNotFound() {

        // given
        when(carService.getCarById(anyLong())).thenReturn(null);

        // when
        ResponseEntity<?> response = carController.deleteCar(1L);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(carService, never()).deleteCarById(anyLong());
    }
}
