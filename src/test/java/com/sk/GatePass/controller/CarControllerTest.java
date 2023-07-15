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

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class CarControllerTest {
    /**
     * Method under test: {@link CarController#getCars()}
     */
    @Test
    void testGetCars() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findAll()).thenReturn(new ArrayList<>());
        CarService carService = new CarService(carRepository);
        ResponseEntity<List<Car>> actualCars = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).getCars();
        assertTrue(actualCars.hasBody());
        assertEquals(200, actualCars.getStatusCodeValue());
        assertTrue(actualCars.getHeaders().isEmpty());
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link CarController#getCars()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCars2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sk.GatePass.service.CarService.getCars()" because "this.carService" is null
        //       at com.sk.GatePass.controller.CarController.getCars(CarController.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        (new CarController(null, new EmployeeService(mock(EmployeeRepository.class)))).getCars();
    }

    /**
     * Method under test: {@link CarController#getCars()}
     */
    @Test
    void testGetCars3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CarService carService = mock(CarService.class);
        when(carService.getCars()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Car>> actualCars = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).getCars();
        assertTrue(actualCars.hasBody());
        assertEquals(200, actualCars.getStatusCodeValue());
        assertTrue(actualCars.getHeaders().isEmpty());
        verify(carService).getCars();
    }

    /**
     * Method under test: {@link CarController#getCar(Long)}
     */
    @Test
    void testGetCar() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#getCar(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCar2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElse(Object)" because the return value of "com.sk.GatePass.repository.CarRepository.findById(Object)" is null
        //       at com.sk.GatePass.service.CarService.getCarById(CarService.java:28)
        //       at com.sk.GatePass.controller.CarController.getCar(CarController.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById((Long) any())).thenReturn(null);
        CarService carService = new CarService(carRepository);
        (new CarController(carService, new EmployeeService(mock(EmployeeRepository.class)))).getCar(1L);
    }

    /**
     * Method under test: {@link CarController#getCar(Long)}
     */
    @Test
    void testGetCar3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#getCar(Long)}
     */
    @Test
    void testGetCar4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CarService carService = mock(CarService.class);
        when(carService.getCarById((Long) any())).thenReturn(new Car());
        ResponseEntity<Car> actualCar = (new CarController(carService,
                new EmployeeService(mock(EmployeeRepository.class)))).getCar(1L);
        assertTrue(actualCar.hasBody());
        assertTrue(actualCar.getHeaders().isEmpty());
        assertEquals(200, actualCar.getStatusCodeValue());
        verify(carService).getCarById((Long) any());
    }

    /**
     * Method under test: {@link CarController#addCar(CarDto)}
     */
    @Test
    void testAddCar() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#addCar(CarDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCar2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sk.GatePass.service.CarService.addCar(com.sk.GatePass.model.Car)" because "this.carService" is null
        //       at com.sk.GatePass.controller.CarController.addCar(CarController.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.of(new Employee()));
        CarController carController = new CarController(null, new EmployeeService(employeeRepository));
        carController.addCar(new CarDto("Brand", "Model", "Plate", 1L));
    }

    /**
     * Method under test: {@link CarController#addCar(CarDto)}
     */
    @Test
    void testAddCar3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#addCar(CarDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCar4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sk.GatePass.service.EmployeeService.getEmployeeById(java.lang.Long)" because "this.employeeService" is null
        //       at com.sk.GatePass.controller.CarController.addCar(CarController.java:52)
        //   See https://diff.blue/R013 to resolve this issue.

        CarService carService = mock(CarService.class);
        when(carService.addCar((Car) any())).thenReturn(new Car());
        CarController carController = new CarController(carService, null);
        carController.addCar(new CarDto("Brand", "Model", "Plate", 1L));
    }

    /**
     * Method under test: {@link CarController#addCar(CarDto)}
     */
    @Test
    void testAddCar5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#addCar(CarDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCar6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sk.GatePass.dto.CarDto.brand()" because "newCar" is null
        //       at com.sk.GatePass.controller.CarController.addCar(CarController.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        CarService carService = mock(CarService.class);
        when(carService.addCar((Car) any())).thenReturn(new Car());
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.getEmployeeById((Long) any())).thenReturn(Optional.of(new Employee()));
        (new CarController(carService, employeeService)).addCar(null);
    }

    /**
     * Method under test: {@link CarController#updateCar(Long, Car)}
     */
    @Test
    void testUpdateCar() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#updateCar(Long, Car)}
     */
    @Test
    void testUpdateCar2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#updateCar(Long, Car)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCar3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.sk.GatePass.service.CarService.updateOldCar(CarService.java:42)
        //       at com.sk.GatePass.service.CarService.updateCarById(CarService.java:36)
        //       at com.sk.GatePass.controller.CarController.updateCar(CarController.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.save((Car) any())).thenReturn(new Car());
        when(carRepository.findById((Long) any())).thenReturn(Optional.empty());
        CarService carService = new CarService(carRepository);
        CarController carController = new CarController(carService, new EmployeeService(mock(EmployeeRepository.class)));
        carController.updateCar(1L, new Car());
    }

    /**
     * Method under test: {@link CarController#updateCar(Long, Car)}
     */
    @Test
    void testUpdateCar4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#deleteCar(Long)}
     */
    @Test
    void testDeleteCar() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#deleteCar(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCar2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElse(Object)" because the return value of "com.sk.GatePass.repository.CarRepository.findById(Object)" is null
        //       at com.sk.GatePass.service.CarService.getCarById(CarService.java:28)
        //       at com.sk.GatePass.controller.CarController.deleteCar(CarController.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        CarRepository carRepository = mock(CarRepository.class);
        doNothing().when(carRepository).deleteById((Long) any());
        when(carRepository.findById((Long) any())).thenReturn(null);
        CarService carService = new CarService(carRepository);
        (new CarController(carService, new EmployeeService(mock(EmployeeRepository.class)))).deleteCar(1L);
    }

    /**
     * Method under test: {@link CarController#deleteCar(Long)}
     */
    @Test
    void testDeleteCar3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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

    /**
     * Method under test: {@link CarController#deleteCar(Long)}
     */
    @Test
    void testDeleteCar4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
}//package com.sk.GatePass.controller;
//
//
//import com.sk.GatePass.model.Car;
//import com.sk.GatePass.model.Employee;
//import com.sk.GatePass.service.CarService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CarControllerTest {
//
//    @Mock
//    private CarService carService;
//
//    @InjectMocks
//    private CarController carController;
//
//    private List<Car> carList;
//    private Car car1;
//    private Employee employee1;
//    private Car car2;
//
//    @BeforeEach
//    void setUp() {
//
//        car1 = new Car(1L,"Fiat","Panda","SK12345",);
//        car2 = new Car("Fiat", "Panda", "SK12345");
//        car2.setId(2L);
//
//    }
//
//    @Test
//    void shouldReturnAllCars() {
//
//        // given
//        carList = new ArrayList<>();
//        carList.add(car1);
//        carList.add(car2);
//        when(carService.getCars()).thenReturn(carList);
//
//        // when
//        ResponseEntity<List<Car>> response = carController.getCars();
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(carList);
//    }
//
//    @Test
//    void shouldReturnCarById() {
//
//        // given
//        when(carService.getCarById(any())).thenReturn(car1);
//
//        // when
//        ResponseEntity<Car> response = carController.getCar(1L);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(car1);
//    }
//
//    @Test
//    void shouldReturnNotFound() {
//
//        // given
//        when(carService.getCarById(anyLong())).thenReturn(null);
//
//        // when
//        ResponseEntity<Car> response = carController.getCar(1L);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void shouldCreateNewCar() {
//
//        // given
//        when(carService.addCar(any(Car.class))).thenReturn(car1);
//
//        // when
//        ResponseEntity<Car> response = carController.addCar(car1);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo(car1);
//    }
//
//    @Test
//    void shouldUpdateExistingCar() {
//
//        // given
//        when(carService.updateCarById(anyLong(), any(Car.class))).thenReturn(car1);
//
//        // when
//        ResponseEntity<Car> response = carController.updateCar(1L, car1);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(car1);
//    }
//
//    @Test
//    void shouldDeleteExistingCar() {
//
//        // given
//        when(carService.getCarById(anyLong())).thenReturn(car1);
//
//        // when
//        ResponseEntity<?> response = carController.deleteCar(1L);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        verify(carService, times(1)).deleteCarById(1L);
//    }
//
//    @Test
//    void shouldReturnCarNotFound() {
//
//        // given
//        when(carService.getCarById(anyLong())).thenReturn(null);
//
//        // when
//        ResponseEntity<?> response = carController.deleteCar(1L);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        verify(carService, never()).deleteCarById(anyLong());
//    }
//}
