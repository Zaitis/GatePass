package com.sk.GatePass.service;

import com.sk.GatePass.model.User;
import com.sk.GatePass.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final PersonRepository personRepository;

    public List<User> getPerson(){
        return personRepository.findAll();

    }

    public User addPerson(User user){

        return personRepository.save(user);
    }

    public User getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public User updatePersonById(Long id, User User) {
        User oldUser =personRepository.findById(id).get();
        oldUser.setName(User.getName());
        oldUser.setSurname(User.getSurname());
        oldUser.setMail(User.getMail());
        oldUser.setPassword(User.getPassword());
        return  personRepository.save(oldUser);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
