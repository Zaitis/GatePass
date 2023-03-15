package com.sk.GatePass.controller;

import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.model.Person;
import com.sk.GatePass.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PersonControllerTest {

    private PersonController personController;
    private Person person1;
    private Person person2;

    @Mock
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personController = new PersonController(personService);
            person1=new Person("Michael", "Smith","michael.smith@test.com","password123");
            person2=new Person("Adam", "Kowalsky","adam.kowalsky@test.com","password456");


    }

    @Test
    public void shouldGetPeople() {
        // Arrange
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        when(personService.getPerson()).thenReturn(people);

        // Act
        ResponseEntity<List<Person>> response = personController.getPeople();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(people, response.getBody());
    }

    @Test
    public void shouldGetPerson() {
        // Arrange
        Long id = 1L;
        when(personService.getPersonById(id)).thenReturn(person1);

        // Act
        ResponseEntity<Person> response = personController.getPerson(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(person1, response.getBody());
    }

    @Test
    public void shouldAddPerson() {
        // Arrange
        Person newPerson = person1;
        Person savedPerson = person2;
        when(personService.addPerson(newPerson)).thenReturn(savedPerson);

        // Act
        ResponseEntity<Person> response = personController.addPerson(newPerson);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedPerson, response.getBody());
    }

    @Test
    public void shouldUpdatePerson() {
        // Arrange
        Long id = 1L;
        Person existingPerson =person1;
        Person updatedPerson = person2;
        when(personService.updatePersonById(id, updatedPerson)).thenReturn(updatedPerson);

        // Act
        ResponseEntity<Person> response = personController.updatePerson(id, updatedPerson);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPerson, response.getBody());
    }

    @Test
    public void shouldDeletePerson() {
        // Arrange
        Long id = 1L;
        Person deletedPerson = person1;
        when(personService.getPersonById(id)).thenReturn(deletedPerson);

        // Act
        ResponseEntity<Person> response = personController.deletePerson(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(personService, times(1)).deletePersonById(anyLong());
    }
}
