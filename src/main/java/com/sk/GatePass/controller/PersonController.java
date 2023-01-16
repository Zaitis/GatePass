package com.sk.GatePass.controller;

import com.sk.GatePass.model.Person;
import com.sk.GatePass.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPeople(){
        List<Person> people = personService.getPerson();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id")Long id){
        Person person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/people")
    public ResponseEntity<Person> addPerson(@RequestBody Person newPerson){
        Person person = personService.addPerson(newPerson);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
