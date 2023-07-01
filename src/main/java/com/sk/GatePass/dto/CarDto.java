package com.sk.GatePass.dto;

import com.sk.GatePass.model.User;

import java.util.List;

public record CarDto(String brand, String model, String plate, List<User> users) {
}
