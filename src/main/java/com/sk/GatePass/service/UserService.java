package com.sk.GatePass.service;
import com.sk.GatePass.model.User;
import com.sk.GatePass.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUserById(Long id, User User) {
        User oldUser = userRepository.findById(id).get();
        oldUser.setName(User.getName());
        oldUser.setSurname(User.getSurname());
        oldUser.setMail(User.getMail());
        oldUser.setPassword(User.getPassword());
        return  userRepository.save(oldUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
