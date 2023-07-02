package com.sk.GatePass.dto;

import com.sk.GatePass.model.Role;
import lombok.Builder;

import java.util.List;


@Builder
public record EmployeeDto(
        String firstName,
        String lastName,
        String phone,
        String mail,
        String password,
        List<CarDto> cars,
        Role role,
        Long company
){}

