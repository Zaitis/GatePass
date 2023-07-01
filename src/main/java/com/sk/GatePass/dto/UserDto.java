package com.sk.GatePass.dto;

import com.sk.GatePass.model.Car;
import lombok.Builder;

import java.util.List;


@Builder
public record UserDto (
        String firstName,
        String lastName,
        String phone,
        String mail,
        String password,
        List<Car> cars
){}

