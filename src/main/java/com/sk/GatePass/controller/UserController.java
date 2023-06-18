package com.sk.GatePass.controller;

import com.sk.GatePass.model.Role;
import com.sk.GatePass.model.User;
import com.sk.GatePass.controller.dto.UserDto;
import com.sk.GatePass.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> people = userService.getUser();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id")Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto newUser){
        User user = userService.addUser(createNewUser(newUser));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id")Long id, @RequestBody UserDto editUser){
        User user = userService.updateUserById(id, createNewUser(editUser));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value ="id") Long id){
        User deleteUser = userService.getUserById(id);
        if (deleteUser !=null){
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private  User createNewUser(UserDto UserDto) {
        return   User.builder()
                .name(UserDto.name())
                .surname(UserDto.surname())
                .mail(UserDto.mail())
                .password(UserDto.password())
                .phone(UserDto.phone())
                .cardNumber(UserDto.cardNumber())
                .role(Role.USER)
                .build();

    }
}
