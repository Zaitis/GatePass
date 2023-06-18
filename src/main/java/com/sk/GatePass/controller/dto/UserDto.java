package com.sk.GatePass.controller.dto;

import lombok.Builder;


@Builder
public record UserDto (
        String name,
        String surname,
        String phone,
        String cardNumber,
        String mail,
        String password
){}

