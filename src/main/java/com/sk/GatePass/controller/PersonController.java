package com.sk.GatePass.controller;

import com.sk.GatePass.model.Car;
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

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value="id")Long id, @RequestBody Person newPerson){
        Person person = personService.updatePersonById(id, newPerson);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable(value ="id") Long id){
        Person deletePerson= personService.getPersonById(id);
        if (deletePerson!=null){
            personService.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
