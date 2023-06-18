package com.sk.GatePass.controller;

import com.sk.GatePass.controller.dto.UserDto;
import com.sk.GatePass.model.Role;
import com.sk.GatePass.model.User;
import com.sk.GatePass.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.util.AssertionErrors.assertNull;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private User user1;
    private User user2;
    private UserDto userDto;
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {

        userController = new UserController(userService);
        user1 = User.builder()
                .name("Adam")
                .surname("Kowalski")
                .mail("a.kowalski@test.com")
                .password("trudnehaslo")
                .phone("123456789")
                .cardNumber("4444")
                .role(Role.USER)
                .build();

        user2 = User.builder()
                .name("Marcin")
                .surname("Nowak")
                .mail("m.nowak@test.com")
                .password("prostehaslo")
                .phone("987654321")
                .cardNumber("6666")
                .role(Role.USER)
                .build();

        userDto = new UserDto(
                "Adam",
                "Kowalski",
                "123456789",
                "4444",
                "a.kowalski@test.com",
                "trudnehaslo");
    }

    @Test
    public void shouldGetAllUsers() {

        //given
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userService.getUser()).thenReturn(users);

        //when
        ResponseEntity<List<User>> response = userController.getUsers();

        //then
        assertEquals(OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void shouldGetUserById() {

        //given
        Long id = 2L;
        when(userService.getUserById(id)).thenReturn(user2);

        //when
        ResponseEntity<User> response = userController.getUser(id);

        // then
        assertEquals(OK, response.getStatusCode());
        assertEquals(user2, response.getBody());
    }


    @Test
    public void shouldAddUser() {

        // given
        User newUser = user1;
        when(userService.addUser(any(User.class))).thenReturn(newUser);

        // when
        ResponseEntity<User> response = userController.addUser(userDto);

        // Then
        assertEquals(CREATED, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }

    @Test
    public void shouldUpdateUser() {

        // given
        Long id = 2L;
        User newUser = user1;

        when(userService.updateUserById(any(Long.class), any(User.class))).thenReturn(newUser);

        // when
        ResponseEntity<User> response = userController.updateUser(id, userDto);

        // then
        assertEquals(OK, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }


    @Test
    void shouldDeleteUser() {

        //given
        User user = user2;
        Long id = 2L;
        when(userService.getUserById(id)).thenReturn(user);

        //when
        ResponseEntity<User> response = userController.deleteUser(id);

        //then
        assertEquals(OK, response.getStatusCode());
        assertNull(null, response.getBody());
    }

    @Test
    void shouldNotFoundUser(){

        //given
        Long id = 2L;
        when(userService.getUserById(id)).thenReturn(null);

        //when
        ResponseEntity<User> response = userController.deleteUser(id);

        //then
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(null, response.getBody());
    }


}
