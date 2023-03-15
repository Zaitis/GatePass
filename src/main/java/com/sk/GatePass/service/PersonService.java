package com.sk.GatePass.service;

import com.sk.GatePass.model.Person;
import com.sk.GatePass.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final PersonRepository personRepository;

    public List<Person> getPerson(){
        return personRepository.findAll();

    }

    public Person addPerson(Person person){

        return personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person updatePersonById(Long id, Person Person) {
        Person oldPerson=personRepository.findById(id).get();
        oldPerson.setName(Person.getName());
        oldPerson.setSurname(Person.getSurname());
        oldPerson.setMail(Person.getMail());
        oldPerson.setPassword(Person.getPassword());
        return  personRepository.save(oldPerson);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
