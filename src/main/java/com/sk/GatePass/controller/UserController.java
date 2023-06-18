package com.sk.GatePass.controller;

import com.sk.GatePass.model.User;
import com.sk.GatePass.service.PersonService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<User>> getPeople(){
        List<User> people = personService.getPerson();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
    @GetMapping("/people/{id}")
    public ResponseEntity<User> getPerson(@PathVariable("id")Long id){
        User user = personService.getPersonById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/people")
    public ResponseEntity<User> addPerson(@RequestBody User newUser){
        User user = personService.addPerson(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<User> updatePerson(@PathVariable(value="id")Long id, @RequestBody User newUser){
        User user = personService.updatePersonById(id, newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<User> deletePerson(@PathVariable(value ="id") Long id){
        User deleteUser = personService.getPersonById(id);
        if (deleteUser !=null){
            personService.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
